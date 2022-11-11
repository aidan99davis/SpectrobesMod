package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.InkanaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.InkanaFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class InkanaFossilItemRenderer extends GeoItemRenderer<InkanaFossilItem> {

    public InkanaFossilItemRenderer() {
        super(new InkanaFossilItemModel());
    }
}
