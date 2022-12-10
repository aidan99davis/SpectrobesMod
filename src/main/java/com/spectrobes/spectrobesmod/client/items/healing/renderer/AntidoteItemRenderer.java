package com.spectrobes.spectrobesmod.client.items.healing.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.spectrobes.spectrobesmod.client.items.healing.model.BasicAntidoteItemModel;
import com.spectrobes.spectrobesmod.client.items.healing.model.BasicSerumItemModel;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeAntidoteHealingItem;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeSerumHealingItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class AntidoteItemRenderer extends GeoItemRenderer<SpectrobeAntidoteHealingItem> {

    public AntidoteItemRenderer() {
        super(new BasicAntidoteItemModel());
    }

    @Override
    public RenderType getRenderType(SpectrobeAntidoteHealingItem animatable, float partialTick, PoseStack poseStack, @org.jetbrains.annotations.Nullable MultiBufferSource bufferSource, @org.jetbrains.annotations.Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}
