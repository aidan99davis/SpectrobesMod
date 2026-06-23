package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.VilarFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.VilarFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class VilarFossilItemRenderer extends GeoItemRenderer<VilarFossilItem> {

    public VilarFossilItemRenderer() {
        super(new VilarFossilItemModel());
    }
}
