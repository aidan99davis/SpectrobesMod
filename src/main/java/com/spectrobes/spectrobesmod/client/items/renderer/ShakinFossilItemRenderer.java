package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.SeguFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.ShakinFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.SeguFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.ShakinFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class ShakinFossilItemRenderer extends GeoItemRenderer<ShakinFossilItem> {

    public ShakinFossilItemRenderer() {
        super(new ShakinFossilItemModel());
    }
}
