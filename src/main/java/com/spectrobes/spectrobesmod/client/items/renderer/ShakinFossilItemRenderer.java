package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.ShakinFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.ShakinFossilItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class ShakinFossilItemRenderer extends GeoItemRenderer<ShakinFossilItem> {

    public ShakinFossilItemRenderer() {
        super(new ShakinFossilItemModel());
    }
}
