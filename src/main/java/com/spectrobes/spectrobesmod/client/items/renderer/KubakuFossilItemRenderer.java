package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.KomainuFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.KubakuFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.KubakuFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class KubakuFossilItemRenderer extends GeoItemRenderer<KubakuFossilItem> {

    public KubakuFossilItemRenderer() {
        super(new KubakuFossilItemModel());
    }
}
