package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.DanawaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.DanawaFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DanawaFossilItemRenderer extends GeoItemRenderer<DanawaFossilItem> {

    public DanawaFossilItemRenderer() {
        super(new DanawaFossilItemModel());
    }
}
