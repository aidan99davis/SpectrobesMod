package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.SpikoFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.VilarFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.SpikoFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.VilarFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class VilarFossilItemRenderer extends GeoItemRenderer<VilarFossilItem> {

    public VilarFossilItemRenderer() {
        super(new VilarFossilItemModel());
    }
}
