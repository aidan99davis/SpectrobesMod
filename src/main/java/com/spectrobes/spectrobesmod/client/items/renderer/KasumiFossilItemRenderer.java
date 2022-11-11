package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.KasumiFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.KasumiFossilItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class KasumiFossilItemRenderer extends GeoItemRenderer<KasumiFossilItem> {

    public KasumiFossilItemRenderer() {
        super(new KasumiFossilItemModel());
    }
}
