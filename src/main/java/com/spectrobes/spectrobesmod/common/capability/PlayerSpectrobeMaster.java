package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerSpectrobeMaster {

    /** The maximum number of owned spectrobes to track. todo: Configurable */
    public static final int MAX_OWNED_SPECTROBES = 50;

    private HashMap<String, List<Spectrobe>>
        ownedSpectrobes;

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

    public CompoundNBT serializeNBT() {
        CompoundNBT myData = new CompoundNBT();
        CompoundNBT ownedSpectrobesNbt = new CompoundNBT();
        for (String key : ownedSpectrobes.keySet()) {
            ListNBT spectrobes = new ListNBT();
            for(Spectrobe s : ownedSpectrobes.get(key)) {
                spectrobes.add(s.write());
            }
            ownedSpectrobesNbt.put(key, spectrobes);
        }
        myData.put("spectrobesOwned", ownedSpectrobesNbt);
        return myData;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        CompoundNBT ownedSpectrobesNbt = (CompoundNBT) nbt.get("spectrobesOwned");
        for(String key : ownedSpectrobesNbt.keySet()) {
            List<Spectrobe> spectrobes = new ArrayList<>();
            for(INBT spectrobeNbt : (ListNBT)ownedSpectrobesNbt.get(key)) {
                spectrobes.add(Spectrobe.read((CompoundNBT)spectrobeNbt));
            }
            ownedSpectrobes.put(key, spectrobes);
        }

    }

    public void copyFrom(PlayerSpectrobeMaster oldStore) {
        ownedSpectrobes = oldStore.ownedSpectrobes;
    }
}