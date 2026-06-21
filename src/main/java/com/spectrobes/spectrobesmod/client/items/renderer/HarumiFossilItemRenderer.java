package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.HarumiFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.HarumiFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HarumiFossilItemRenderer extends GeoItemRenderer<HarumiFossilItem> {

    public HarumiFossilItemRenderer() {
        super(new HarumiFossilItemModel());
    }
}
