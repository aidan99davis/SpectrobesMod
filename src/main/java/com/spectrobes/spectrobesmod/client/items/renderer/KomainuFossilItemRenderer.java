package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.HarumiFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.KomainuFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.HarumiFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class KomainuFossilItemRenderer extends GeoItemRenderer<KomainuFossilItem> {

    public KomainuFossilItemRenderer() {
        super(new KomainuFossilItemModel());
    }
}
