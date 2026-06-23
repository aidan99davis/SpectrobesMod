package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.InkanaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.InkanaFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class InkanaFossilItemRenderer extends GeoItemRenderer<InkanaFossilItem> {

    public InkanaFossilItemRenderer() {
        super(new InkanaFossilItemModel());
    }
}
