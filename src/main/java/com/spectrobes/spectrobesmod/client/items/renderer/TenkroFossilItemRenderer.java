package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.TenkroFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.TenkroFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class TenkroFossilItemRenderer extends GeoItemRenderer<TenkroFossilItem> {

    public TenkroFossilItemRenderer() {
        super(new TenkroFossilItemModel());
    }
}
