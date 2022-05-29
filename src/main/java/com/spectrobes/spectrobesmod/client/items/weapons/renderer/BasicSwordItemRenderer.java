package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.BasicSwordItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicSwordItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class BasicSwordItemRenderer extends GeoItemRenderer<BasicSwordItem> {

    public BasicSwordItemRenderer() {
        super(new BasicSwordItemModel());
    }
}
