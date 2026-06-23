package com.spectrobes.spectrobesmod.client.items.healing.renderer;

import com.spectrobes.spectrobesmod.client.items.healing.model.BasicAntidoteItemModel;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeAntidoteHealingItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class AntidoteItemRenderer extends GeoItemRenderer<SpectrobeAntidoteHealingItem> {

    public AntidoteItemRenderer() {
        super(new BasicAntidoteItemModel());
    }

    @Override
    public @Nullable RenderType getRenderType(SpectrobeAntidoteHealingItem animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
