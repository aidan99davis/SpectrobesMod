package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MesaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.MesaFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class MesaFossilItemRenderer extends GeoItemRenderer<MesaFossilItem> {

    public MesaFossilItemRenderer() {
        super(new MesaFossilItemModel());
    }
}
