package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.SpikoFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.SpikoFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SpikoFossilItemRenderer extends GeoItemRenderer<SpikoFossilItem> {

    public SpikoFossilItemRenderer() {
        super(new SpikoFossilItemModel());
    }
}
