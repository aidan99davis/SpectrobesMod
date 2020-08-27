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
    }

    public void addSpectrobe(Spectrobe spectrobeInstance) {
        SpectrobesInfo.LOGGER.info("adding spectrobe");
        ownedSpectrobes.add(spectrobeInstance);
    }

    public void setTeamMember(int index, Spectrobe member) {
        if(member != null) {
            currentTeam.put(index, member.SpectrobeUUID);
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

        for(int i : currentTeam.keySet()) {
            currentTeamNbt.putUniqueId(String.valueOf(i), currentTeam.get(i));
        }

        myData.put("currentTeam", currentTeamNbt);
        myData.put("spectrobesOwned", spectrobes);
        return myData;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        this.ownedSpectrobes = new ArrayList<>();
        ListNBT ownedSpectrobesNbt = (ListNBT) nbt.get("spectrobesOwned");
        CompoundNBT currentTeamNbt = (CompoundNBT) nbt.get("currentTeam");
        List<Spectrobe> spectrobes = new ArrayList<>();
        for(INBT spectrobeNbt : ownedSpectrobesNbt) {
            spectrobes.add(Spectrobe.read((CompoundNBT)spectrobeNbt));
        }
        ownedSpectrobes.addAll(spectrobes);
        if(currentTeamNbt != null) {
            Set<String> keys = currentTeamNbt.keySet();
            for(String s : keys) {
                int index = Integer.valueOf(s.substring(0,1));
                currentTeam.put(index, currentTeamNbt.getUniqueId(s));
            }
        }

    }

    public void copyFrom(PlayerSpectrobeMaster oldStore) {
        ownedSpectrobes = oldStore.ownedSpectrobes;
    }

    public int getOwnedSpectrobesCount() {
        return ownedSpectrobes.size();
    }

    public Map<Integer, UUID> getCurrentTeamUuids() {
        return currentTeam;
    }

    public void updateSpectrobe(Spectrobe spectrobeInstance) {
        for (Spectrobe s : getOwnedSpectrobes()) {
            if(s.SpectrobeUUID == spectrobeInstance.SpectrobeUUID) {
                ownedSpectrobes.remove(s);
                ownedSpectrobes.add(spectrobeInstance);
            }
        }
    }

    public void setSpectrobeInactive(Spectrobe spectrobeData) {
        this.ownedSpectrobes.forEach(s -> {
            if(s.SpectrobeUUID == spectrobeData.SpectrobeUUID) {
                ownedSpectrobes.remove(s);
                s.active = false;
                ownedSpectrobes.add(spectrobeData);
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