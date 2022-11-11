package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.DongorFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.DongorFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DongorFossilItemRenderer extends GeoItemRenderer<DongorFossilItem> {

    public DongorFossilItemRenderer() {
        super(new DongorFossilItemModel());
    }
}
