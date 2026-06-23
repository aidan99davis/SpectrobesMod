package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.BartorFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.BartorFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BartorFossilItemRenderer extends GeoItemRenderer<BartorFossilItem> {

    public BartorFossilItemRenderer() {
        super(new BartorFossilItemModel());
    }
}
