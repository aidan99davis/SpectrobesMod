package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.TenkroFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.TenkroFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class TenkroFossilItemRenderer extends GeoItemRenderer<TenkroFossilItem> {

    public TenkroFossilItemRenderer() {
        super(new TenkroFossilItemModel());
    }
}
