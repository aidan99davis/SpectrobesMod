package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.BasicBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicBlasterItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class BasicBlasterItemRenderer extends GeoItemRenderer<BasicBlasterItem> {

    public BasicBlasterItemRenderer() {
        super(new BasicBlasterItemModel());
    }
}
