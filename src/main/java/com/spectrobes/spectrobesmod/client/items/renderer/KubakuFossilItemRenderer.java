package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.KubakuFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.KubakuFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class KubakuFossilItemRenderer extends GeoItemRenderer<KubakuFossilItem> {

    public KubakuFossilItemRenderer() {
        super(new KubakuFossilItemModel());
    }
}
