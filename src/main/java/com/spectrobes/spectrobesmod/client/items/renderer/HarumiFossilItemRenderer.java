package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.GrildaFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.HarumiFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.GrildaFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.HarumiFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class HarumiFossilItemRenderer extends GeoItemRenderer<HarumiFossilItem> {

    public HarumiFossilItemRenderer() {
        super(new HarumiFossilItemModel());
    }
}
