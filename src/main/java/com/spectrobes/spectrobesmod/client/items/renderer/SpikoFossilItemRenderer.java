package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.ShakinFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.SpikoFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.ShakinFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.SpikoFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class SpikoFossilItemRenderer extends GeoItemRenderer<SpikoFossilItem> {

    public SpikoFossilItemRenderer() {
        super(new SpikoFossilItemModel());
    }
}
