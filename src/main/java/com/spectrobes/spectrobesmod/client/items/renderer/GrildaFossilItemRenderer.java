package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.GrildaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.GrildaFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GrildaFossilItemRenderer extends GeoItemRenderer<GrildaFossilItem> {

    public GrildaFossilItemRenderer() {
        super(new GrildaFossilItemModel());
    }
}
