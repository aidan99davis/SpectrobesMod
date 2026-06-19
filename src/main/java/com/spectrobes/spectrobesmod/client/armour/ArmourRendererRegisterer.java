
package com.spectrobes.spectrobesmod.client.armour;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.armour.renderer.BasicNppArmourRenderer;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

@EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, value = Dist.CLIENT)
public class ArmourRendererRegisterer {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(BasicNppArmourItem.class, BasicNppArmourRenderer::new);
    }
}
