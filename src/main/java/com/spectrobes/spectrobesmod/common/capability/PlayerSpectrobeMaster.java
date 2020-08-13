package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerSpectrobeMaster {

    /** The maximum number of owned spectrobes to track. todo: Configurable */
    public static final int MAX_OWNED_SPECTROBES = 50;

    //index 0 & 1 : current fighting spectrobes
    //index 2-6 : 4 back up fighting spectrobes
    //index 7 : child form
    private UUID[] currentTeam = new UUID[7];

    private List<Spectrobe>
        ownedSpectrobes;

    public boolean canFight() {
        return currentTeam[0] != null && currentTeam[1] != null;
    }

    public PlayerSpectrobeMaster() {
        this.ownedSpectrobes = new ArrayList<>();

    }

    public boolean addSpectrobe(String entityTypeAsString,
                                Spectrobe spectrobeInstance) {
        SpectrobesInfo.LOGGER.info("ADDING SPECTROBE TO THE PLAYER");
        List<Spectrobe> newList;
        if(ownedSpectrobes.size() + 1 <= MAX_OWNED_SPECTROBES) {
            ownedSpectrobes.add(spectrobeInstance);
            return true;
        }
        return false;
    }

    public void setTeamMember(int index, Spectrobe member) {
        currentTeam[index] = member.SpectrobeUUID;
    }

    public void removeTeamMember(int index) {
        currentTeam[index] = null;
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
        int index = 0;
        for(UUID s : currentTeam) {
            currentTeamNbt.putUniqueId(String.valueOf(index), s);
            index++;
        }
        myData.put("currentTeam", currentTeamNbt);
        myData.put("spectrobesOwned", spectrobes);
        return myData;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        ListNBT ownedSpectrobesNbt = (ListNBT) nbt.get("spectrobesOwned");
        CompoundNBT currentTeamNbt = (CompoundNBT) nbt.get("currentTeam");
        List<Spectrobe> spectrobes = new ArrayList<>();
        for(INBT spectrobeNbt : (ListNBT)ownedSpectrobesNbt) {
            spectrobes.add(Spectrobe.read((CompoundNBT)spectrobeNbt));
        }
        ownedSpectrobes.addAll(spectrobes);
        if(currentTeamNbt != null) {
            for(int i = 0; (i < currentTeamNbt.keySet().size() && i <= 6); i++) {
                currentTeam[i] = (currentTeamNbt.getUniqueId(String.valueOf(i)));
            }
        }

    }

    public void copyFrom(PlayerSpectrobeMaster oldStore) {
        ownedSpectrobes = oldStore.ownedSpectrobes;
    }

    public int getOwnedSpectrobesCount() {
        return ownedSpectrobes.size();
    }

    public UUID[] getCurrentTeamUuids() {
        return currentTeam;
    }

    public void updateSpectrobe(String entityTypeAsString, Spectrobe spectrobeInstance) {
        for (Spectrobe s : getOwnedSpectrobes()) {
            if(s.SpectrobeUUID == spectrobeInstance.SpectrobeUUID) {
                SpectrobesInfo.LOGGER.info("UPDATING YER TROBE");
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