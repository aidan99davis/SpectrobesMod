package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MesaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.MesaFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MesaFossilItemRenderer extends GeoItemRenderer<MesaFossilItem> {

    public MesaFossilItemRenderer() {
        super(new MesaFossilItemModel());
    }
}
