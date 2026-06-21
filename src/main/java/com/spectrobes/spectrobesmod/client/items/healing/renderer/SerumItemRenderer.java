package com.spectrobes.spectrobesmod.client.items.healing.renderer;

import com.spectrobes.spectrobesmod.client.items.healing.model.BasicSerumItemModel;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeSerumHealingItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SerumItemRenderer extends GeoItemRenderer<SpectrobeSerumHealingItem> {

    public SerumItemRenderer() {
        super(new BasicSerumItemModel());
    }

    @Override
    public @Nullable RenderType getRenderType(SpectrobeSerumHealingItem animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
