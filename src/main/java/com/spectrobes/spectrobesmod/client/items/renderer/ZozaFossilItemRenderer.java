package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.VilarFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.ZozaFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.VilarFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.ZozaFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class ZozaFossilItemRenderer extends GeoItemRenderer<ZozaFossilItem> {

    public ZozaFossilItemRenderer() {
        super(new ZozaFossilItemModel());
    }
}
