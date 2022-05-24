package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.BartorFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.BartorFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class BartorFossilItemRenderer extends GeoItemRenderer<BartorFossilItem> {

    public BartorFossilItemRenderer() {
        super(new BartorFossilItemModel());
    }
}
