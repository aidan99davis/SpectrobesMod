package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerSpectrobeMaster {

    //index 0 & 1 : current fighting spectrobes
    //index 2-5 : 4 back up fighting spectrobes
    //index 6 : child form
    private Map<Integer,UUID> currentTeam = new HashMap<>(7);

    private int currentSelected;
    private int guraBalance;

    private int currentHealth = 200;
    private int level = 0;
    private int currentXp = 0;
    private int xp_required = 100;

    private List<Spectrobe>
        ownedSpectrobes;

    public boolean canFight() {
        return ownedSpectrobes
                .stream()
                .anyMatch(spectrobe ->
                        spectrobe.properties.getStage()
                                != SpectrobeProperties.Stage.CHILD);
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

    public int usedTeamSlots() {
        AtomicInteger teamMembers = new AtomicInteger();
        this.getCurrentTeamUuids().forEach((integer, uuid) -> {
            if(uuid != null && integer != 6) teamMembers.getAndIncrement();
        });
        return teamMembers.get();
    }

    public double averageBattleTeamLevel() {
        final double[] total = {0};
        this.getCurrentTeamUuids().forEach((integer, uuid) -> {
            if(uuid != null && integer != 6) {
                total[0] = total[0] + this.getSpectrobeByUuid(uuid).stats.getLevel();
            }
        });

        return total[0] / usedTeamSlots();
    }

    public PlayerSpectrobeMaster() {
        this.ownedSpectrobes = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            this.currentTeam.put(i, null);
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
        myData.putInt("guraBalance", guraBalance);
        myData.put("spectrobesOwned", spectrobes);
        myData.put("currentTeam", currentTeamNbt);
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
        currentSelected = selected;

        int gura;
        try {
            gura = nbt.getInt("guraBalance");
        } catch(NullPointerException ex) {
            gura = 0;
        }
        guraBalance = gura;

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


    public Spectrobe getSpectrobeByUuid(UUID uuid) {
        for (Spectrobe s : getOwnedSpectrobes()) {
            if(s.SpectrobeUUID.equals(uuid)) {
                return s;
            }
        }
        return null;
    }

    public int getCurrentTeamMemberSlot() {
        return currentSelected;
    }

    public int getCurrentGuraBalance() {
        return guraBalance;
    }

    public void addGura(int gura) {
        this.guraBalance = this.guraBalance + gura;
    }

    public boolean spendGura(int gura) {
        if(this.guraBalance - gura >= 0) {
            this.guraBalance = this.guraBalance - gura;
            return true;
        } else {
            return false;
        }
    }
}