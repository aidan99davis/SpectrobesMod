package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
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
    private List<UUID> currentTeam = new ArrayList<>(7);

    private HashMap<String, List<Spectrobe>>
        ownedSpectrobes;

    public boolean canFight() {
        return currentTeam.size() > 2;
    }

    public PlayerSpectrobeMaster() {
        this.ownedSpectrobes = new HashMap<>();

    }

    public boolean addSpectrobe(String entityTypeAsString,
                                Spectrobe spectrobeInstance) {
        SpectrobesInfo.LOGGER.info("ADDING SPECTROBE TO THE PLAYER");
        List<Spectrobe> newList;
        if(ownedSpectrobes.values().size() + 1 <= MAX_OWNED_SPECTROBES) {
            if(this.ownedSpectrobes.get(entityTypeAsString) == null) {
                newList = new ArrayList<>();
                newList.add(spectrobeInstance);
                this.ownedSpectrobes.put(entityTypeAsString, newList);
            } else {
                this.ownedSpectrobes.get(entityTypeAsString).add(spectrobeInstance);
            }
            return true;
        }
        return false;
    }

    public void setTeamMember(int index, Spectrobe member) {
        currentTeam.add(index, member.SpectrobeUUID);
    }

    public void removeTeamMember(int index) {
        currentTeam.remove(index);
    }

    public List<Spectrobe> getOwnedSpectrobes() {
        List<Spectrobe> allOwned =  new ArrayList<>();

        if(ownedSpectrobes.keySet().size() > 0) {
            for(List<Spectrobe> spectrobeList : ownedSpectrobes.values()) {
                allOwned.addAll(spectrobeList);
            }
        }

        return allOwned;
    }

    public CompoundNBT serializeNBT() {
        CompoundNBT myData = new CompoundNBT();
        CompoundNBT ownedSpectrobesNbt = new CompoundNBT();
        CompoundNBT currentTeamNbt = new CompoundNBT();
        for (String key : ownedSpectrobes.keySet()) {
            ListNBT spectrobes = new ListNBT();
            for(Spectrobe s : ownedSpectrobes.get(key)) {
                spectrobes.add(s.write());
            }
            ownedSpectrobesNbt.put(key, spectrobes);
        }
        int index = 0;
        for(UUID s : currentTeam) {
            currentTeamNbt.putUniqueId(String.valueOf(index), s);
            index++;
        }
        myData.put("currentTeam", currentTeamNbt);
        myData.put("spectrobesOwned", ownedSpectrobesNbt);
        return myData;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        CompoundNBT ownedSpectrobesNbt = (CompoundNBT) nbt.get("spectrobesOwned");
        CompoundNBT currentTeamNbt = (CompoundNBT) nbt.get("currentTeam");
        for(String key : ownedSpectrobesNbt.keySet()) {
            List<Spectrobe> spectrobes = new ArrayList<>();
            for(INBT spectrobeNbt : (ListNBT)ownedSpectrobesNbt.get(key)) {
                spectrobes.add(Spectrobe.read((CompoundNBT)spectrobeNbt));
            }
            ownedSpectrobes.put(key, spectrobes);
        }
        if(currentTeamNbt != null) {
            for(int i = 0; (i < currentTeamNbt.keySet().size() && i <= 6); i++) {
                currentTeam.add(currentTeamNbt.getUniqueId(String.valueOf(i)));
            }
        }

    }

    public void copyFrom(PlayerSpectrobeMaster oldStore) {
        ownedSpectrobes = oldStore.ownedSpectrobes;
    }

    public int getOwnedSpectrobesCount() {
        return ownedSpectrobes.values().size();
    }

    public List<UUID> getCurrentTeamUuids() {
        return currentTeam;
    }
}