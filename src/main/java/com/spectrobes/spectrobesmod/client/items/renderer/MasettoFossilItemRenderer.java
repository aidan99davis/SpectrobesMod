package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MasettoFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.MasettoFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MasettoFossilItemRenderer extends GeoItemRenderer<MasettoFossilItem> {

    public MasettoFossilItemRenderer() {
        super(new MasettoFossilItemModel());
    }
}
