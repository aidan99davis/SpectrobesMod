package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MiniXellesItemModel;
import com.spectrobes.spectrobesmod.common.items.special.MiniXellesItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class MiniXellesItemRenderer extends GeoItemRenderer<MiniXellesItem> {

    public MiniXellesItemRenderer() {
        super(new MiniXellesItemModel());
    }
}
