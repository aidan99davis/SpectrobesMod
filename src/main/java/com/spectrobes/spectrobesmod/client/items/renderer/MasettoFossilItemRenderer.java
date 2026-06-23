package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MasettoFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.MasettoFossilItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MasettoFossilItemRenderer extends GeoItemRenderer<MasettoFossilItem> {

    public MasettoFossilItemRenderer() {
        super(new MasettoFossilItemModel());
    }
}
