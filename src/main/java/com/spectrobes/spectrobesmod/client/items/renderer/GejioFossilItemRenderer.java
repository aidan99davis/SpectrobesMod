package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.GejioFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.GejioFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GejioFossilItemRenderer extends GeoItemRenderer<GejioFossilItem> {

    public GejioFossilItemRenderer() {
        super(new GejioFossilItemModel());
    }
}
