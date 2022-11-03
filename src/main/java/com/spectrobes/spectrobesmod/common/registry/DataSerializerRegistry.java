package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//@ObjectHolder(SpectrobesInfo.MOD_ID)
public class DataSerializerRegistry {

//    @SubscribeEvent
//    public static void registerItems(final RegisterEvent.<IDataSerializer<Spectrobe>> event) {
//        event.getRegistry().register(Spectrobe.SpectrobeSerializer);
//    }
}
