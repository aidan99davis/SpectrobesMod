package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.ZozaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.ZozaFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ZozaFossilItemRenderer extends GeoItemRenderer<ZozaFossilItem> {

    public ZozaFossilItemRenderer() {
        super(new ZozaFossilItemModel());
    }
}
