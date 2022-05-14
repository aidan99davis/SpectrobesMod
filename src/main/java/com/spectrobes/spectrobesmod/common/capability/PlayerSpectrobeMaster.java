package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;

import java.util.*;

public class PlayerSpectrobeMaster {

    //index 0 & 1 : current fighting spectrobes
    //index 2-5 : 4 back up fighting spectrobes
    //index 6 : child form
    private Map<Integer,UUID> currentTeam = new HashMap<>(7);

    private int playerGura;

    private int currentSelected;

    private List<Spectrobe>
        ownedSpectrobes;

    public PlayerSpectrobeMaster() {
        this.ownedSpectrobes = new ArrayList<>();
        this.playerGura = 0;
        for(int i = 0; i < 7; i++) {
            this.currentTeam.put(i, null);
        }
    }

    public int getPlayerGura() {
        return playerGura;
    }

    public void addGura(int guraWorth) {
        this.playerGura = playerGura + guraWorth;
    }

    public boolean spendGura(int guraCost) {
        if(this.playerGura - guraCost >= 0) {
            this.playerGura = this.playerGura - guraCost;
            return true;
        }
        return false;
    }

    public boolean canFight() {
        return currentTeam.get(0) != null && currentTeam.get(1) != null;
    }

    public Spectrobe getCurrentTeamMember() {
        UUID selectedUUID = getCurrentTeamUuids().get(currentSelected);

        for(Spectrobe s : ownedSpectrobes) {
            if(s.SpectrobeUUID.equals(selectedUUID)) {
                return s;
            }
        }
        return null;
    }

    public void changeSelected(int direction) {
        if(direction != -1 && direction != 1) {
            SpectrobesInfo.LOGGER.error("UNKNOWN DIRECTION");
            return;
        }
        currentSelected += direction;

        if(currentSelected == 7) {
            currentSelected = 0;
        }
        if(currentSelected == -1) {
            currentSelected = 6;
        }
    }

    public void addSpectrobe(Spectrobe spectrobeInstance) {
        ownedSpectrobes.add(spectrobeInstance);
    }

    public void setTeamMember(int index, UUID spectrobeUUID) {
        if(spectrobeUUID != null) {
            currentTeam.replace(index, spectrobeUUID);
        } else {
            removeTeamMember(index);
        }
    }

    public void removeTeamMember(int index) {
        currentTeam.remove(index);
    }

    public List<Spectrobe> getOwnedSpectrobes() {

        if(ownedSpectrobes.size() > 0) {
            return ownedSpectrobes;
        }

        return new ArrayList<>();
    }

    public int getOwnedSpectrobesCount() {
        return ownedSpectrobes.size();
    }

    public Map<Integer, UUID> getCurrentTeamUuids() {
        return currentTeam;
    }

    public void updateSpectrobe(Spectrobe spectrobeInstance) {
        for (Spectrobe s : getOwnedSpectrobes()) {
            if(s.SpectrobeUUID.equals(spectrobeInstance.SpectrobeUUID)) {
                s.update(spectrobeInstance);
                if(getCurrentTeamUuids().containsValue(s.SpectrobeUUID)) {
                    validateTeam();
                }
            }
        }
    }

    private void validateTeam() {
        for(Spectrobe s : ownedSpectrobes) {
            if(currentTeam.get(6) != null && currentTeam.get(6).equals(s.SpectrobeUUID)) {
                if(s.properties.getStage() != SpectrobeProperties.Stage.CHILD) {
                    removeTeamMember(6);
                }
            }
        }
    }

    public void setSpectrobeInactive(Spectrobe spectrobeData) {
        this.ownedSpectrobes.forEach(s -> {
            if(s.SpectrobeUUID.equals(spectrobeData.SpectrobeUUID)) {
                s.active = false;
            }
        });
    }

    public void spawnSpectrobe(Spectrobe spectrobeData) {
        this.ownedSpectrobes.forEach(s -> {
            if(s.SpectrobeUUID == spectrobeData.SpectrobeUUID) {
                s.active = true;
                return;
            }
        });
    }

    public void releaseSpectrobe(Spectrobe spectrobe) {
        this.ownedSpectrobes.removeIf(s -> s.SpectrobeUUID == spectrobe.SpectrobeUUID);
    }

    public void spawnCurrent() {
        getCurrentTeamMember().setActive();
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT myData = new CompoundNBT();
        CompoundNBT currentTeamNbt = new CompoundNBT();
        ListNBT spectrobes = new ListNBT();
        for(Spectrobe s : ownedSpectrobes) {
            spectrobes.add(s.write());
        }

        for(int i = 0; i < 7; i++) {
            if(currentTeam.get(i) != null)
                currentTeamNbt.putString(String.valueOf(i), currentTeam.get(i).toString());
        }
        myData.putInt("currentSelected", currentSelected);
        myData.put("spectrobesOwned", spectrobes);
        myData.put("currentTeam", currentTeamNbt);
        myData.putInt("playerGura", playerGura);
        return myData;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.ownedSpectrobes = new ArrayList<>();
        this.currentTeam = new HashMap<>(7);
        for(int i = 0; i < 7; i++) {
            this.currentTeam.put(i, null);
        }
        ListNBT ownedSpectrobesNbt = (ListNBT) nbt.get("spectrobesOwned");
        CompoundNBT currentTeamNbt = (CompoundNBT) nbt.get("currentTeam");

        int selected;
        try {
            selected = nbt.getInt("currentSelected");
        } catch(NullPointerException ex) {
            selected = 0;
        }

        int gura;
        try {
            gura = nbt.getInt("playerGura");
        } catch(NullPointerException ex) {
            gura = 0;
        }

        currentSelected = selected;
        playerGura = gura;

        List<Spectrobe> spectrobes = new ArrayList<>();
        for(INBT spectrobeNbt : ownedSpectrobesNbt) {
            spectrobes.add(Spectrobe.read((CompoundNBT)spectrobeNbt));
        }
        ownedSpectrobes.addAll(spectrobes);

        for(int i = 0; i < 7; i++) {
            try {
                currentTeam.replace(i, UUID.fromString(
                        currentTeamNbt.getString(String.valueOf(i))));

            } catch(Exception ex){
            }
        }
    }

    public void copyFrom(PlayerSpectrobeMaster oldStore) {
        ownedSpectrobes = oldStore.ownedSpectrobes;
        currentTeam = oldStore.currentTeam;
        currentSelected = oldStore.currentSelected;
        playerGura = oldStore.playerGura;
    }
}