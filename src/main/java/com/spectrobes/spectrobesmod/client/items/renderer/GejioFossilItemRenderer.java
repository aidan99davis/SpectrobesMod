package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.GejioFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.GejioFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GejioFossilItemRenderer extends GeoItemRenderer<GejioFossilItem> {

    public GejioFossilItemRenderer() {
        super(new GejioFossilItemModel());
    }
}
