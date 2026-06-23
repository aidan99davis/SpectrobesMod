package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.GrildaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.GrildaFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GrildaFossilItemRenderer extends GeoItemRenderer<GrildaFossilItem> {

    public GrildaFossilItemRenderer() {
        super(new GrildaFossilItemModel());
    }
}
