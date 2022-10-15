package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.BasicGloveItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicGloveItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class BasicGloveItemRenderer extends GeoItemRenderer<BasicGloveItem> {

    public BasicGloveItemRenderer() {
        super(new BasicGloveItemModel());
    }
}
