package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MiniXellesItemModel;
import com.spectrobes.spectrobesmod.common.items.special.MiniXellesItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class MiniXellesItemRenderer extends GeoItemRenderer<MiniXellesItem> {

    public MiniXellesItemRenderer() {
        super(new MiniXellesItemModel());
    }
}
