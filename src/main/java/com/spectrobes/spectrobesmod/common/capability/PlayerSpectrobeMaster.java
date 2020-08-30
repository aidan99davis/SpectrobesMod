package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;

import java.util.*;

public class PlayerSpectrobeMaster {

    /** The maximum number of owned spectrobes to track. todo: Configurable */
    public static final int MAX_OWNED_SPECTROBES = 50;

    //index 0 & 1 : current fighting spectrobes
    //index 2-6 : 4 back up fighting spectrobes
    //index 7 : child form
    private Map<Integer,UUID> currentTeam = new HashMap<>(7);

    private List<Spectrobe>
        ownedSpectrobes;

    public boolean canFight() {
        return currentTeam.get(0) != null && currentTeam.get(1) != null;
    }

    public PlayerSpectrobeMaster() {
        this.ownedSpectrobes = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            this.currentTeam.put(i, null);
        }
    }

    public void addSpectrobe(Spectrobe spectrobeInstance) {
//        SpectrobesInfo.LOGGER.info("adding spectrobe");
        ownedSpectrobes.add(spectrobeInstance);
    }

    public void setTeamMember(int index, UUID spectrobeUUID) {
        if(spectrobeUUID != null) {
            SpectrobesInfo.LOGGER.info("Set team member in slot: "
                    + index + " with spectrobe uuid: " + spectrobeUUID);
            currentTeam.replace(index, spectrobeUUID);
        } else {
            SpectrobesInfo.LOGGER.info("Removing team member");
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
            SpectrobesInfo.LOGGER.info("Serialissing: " + String.valueOf(i));
            SpectrobesInfo.LOGGER.info("currentTeam.get(i): " + currentTeam.get(i));
            if(currentTeam.get(i) != null)
                currentTeamNbt.putString(String.valueOf(i), currentTeam.get(i).toString());
        }
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
        List<Spectrobe> spectrobes = new ArrayList<>();
        for(INBT spectrobeNbt : ownedSpectrobesNbt) {
            spectrobes.add(Spectrobe.read((CompoundNBT)spectrobeNbt));
        }
        ownedSpectrobes.addAll(spectrobes);
//        if(currentTeamNbt != null) {
        for(String key : currentTeamNbt.keySet()) {
            SpectrobesInfo.LOGGER.info("KEY: " + key);
        }
        for(int i = 0; i < 7; i++) {
                SpectrobesInfo.LOGGER.info("deserialising: " + i);
                    try {
                        SpectrobesInfo.LOGGER.info("setting spectrobe in slot: " + i + " uuid: "
                                + currentTeamNbt.getString(String.valueOf(i)));
                        currentTeam.replace(i, UUID.fromString(
                                currentTeamNbt.getString(String.valueOf(i))));

                    } catch(Exception ex){
                    }
            }
//        }

    }

    public void copyFrom(PlayerSpectrobeMaster oldStore) {
        ownedSpectrobes = oldStore.ownedSpectrobes;
        currentTeam = oldStore.currentTeam;

    }

    public int getOwnedSpectrobesCount() {
        return ownedSpectrobes.size();
    }

    public Map<Integer, UUID> getCurrentTeamUuids() {
        return currentTeam;
    }

    public void updateSpectrobe(Spectrobe spectrobeInstance) {
        SpectrobesInfo.LOGGER.info("UPDATING SPECTROBE:" + spectrobeInstance.SpectrobeUUID);
        for (Spectrobe s : getOwnedSpectrobes()) {
            SpectrobesInfo.LOGGER.info("UPDATING SPECTROBE PT 2:" + s.SpectrobeUUID);
            if(s.SpectrobeUUID.equals(spectrobeInstance.SpectrobeUUID)) {
                SpectrobesInfo.LOGGER.info("UPDATING SPECTROBE PT 3");
                s.update(spectrobeInstance);
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
            }
        });
    }
}