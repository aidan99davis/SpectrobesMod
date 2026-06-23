package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.DongorFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.DongorFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DongorFossilItemRenderer extends GeoItemRenderer<DongorFossilItem> {

    public DongorFossilItemRenderer() {
        super(new DongorFossilItemModel());
    }
}
