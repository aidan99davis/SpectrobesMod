package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpectrobeMasterCapability implements INBTSerializable<CompoundNBT> {
    @CapabilityInject(SpectrobeMasterCapability.class)
    private static final Capability<SpectrobeMasterCapability> SPECTROBE_MASTER_CAPABILITY = null;

    /** The maximum number of owned spectrobes to track. */
    public static final int MAX_OWNED_SPECTROBES = 50;

    /** The player this SpectrobeMasterCapability instance belongs to. */
    private PlayerEntity player;

    private HashMap<String, List<Spectrobe>>
        ownedSpectrobes;

    public SpectrobeMasterCapability() {
        this(null);
    }

    public SpectrobeMasterCapability(PlayerEntity player) {
        this.player = player;
        this.ownedSpectrobes = new HashMap<>();
    }

    /** Called from preInit in the main mod class to register the SpectrobeMasterCapability capability. */
    public static void register(){

        // Yes - by the looks of it, having an interface is completely unnecessary in this case.
        CapabilityManager.INSTANCE.register(SpectrobeMasterCapability.class, new Capability.IStorage<SpectrobeMasterCapability>(){
            /**
             * Serialize the capability instance to a NBTTag.
             * This allows for a central implementation of saving the data.
             * <p>
             * It is important to note that it is up to the API defining
             * the capability what requirements the 'instance' value must have.
             * <p>
             * Due to the possibility of manipulating internal data, some
             * implementations MAY require that the 'instance' be an instance
             * of the 'default' implementation.
             * <p>
             * Review the API docs for more info.
             *
             * @param capability The Capability being stored.
             * @param instance   An instance of that capabilities interface.
             * @param side       The side of the object the instance is associated with.
             * @return a NBT holding the data. Null if no data needs to be stored.
             */
            @Nullable
            @Override
            public INBT writeNBT(Capability<SpectrobeMasterCapability> capability, SpectrobeMasterCapability instance, Direction side) {
                return null;
            }

            /**
             * Read the capability instance from a NBT tag.
             * <p>
             * This allows for a central implementation of saving the data.
             * <p>
             * It is important to note that it is up to the API defining
             * the capability what requirements the 'instance' value must have.
             * <p>
             * Due to the possibility of manipulating internal data, some
             * implementations MAY require that the 'instance' be an instance
             * of the 'default' implementation.
             * <p>
             * Review the API docs for more info.         *
             *
             * @param capability The Capability being stored.
             * @param instance   An instance of that capabilities interface.
             * @param side       The side of the object the instance is associated with.
             * @param nbt        A NBT holding the data. Must not be null, as doesn't make sense to call this function with nothing to read...
             */
            @Override
            public void readNBT(Capability<SpectrobeMasterCapability> capability, SpectrobeMasterCapability instance, Direction side, INBT nbt) {

            }

        }, SpectrobeMasterCapability::new);
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

    @Override
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

    @Override
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
}