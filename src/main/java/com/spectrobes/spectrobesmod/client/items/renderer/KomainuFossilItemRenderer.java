package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.KomainuFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class KomainuFossilItemRenderer extends GeoItemRenderer<KomainuFossilItem> {

    public KomainuFossilItemRenderer() {
        super(new KomainuFossilItemModel());
    }
}
