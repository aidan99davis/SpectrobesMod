package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.DanawaFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.DongorFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.DanawaFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.DongorFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DanawaFossilItemRenderer extends GeoItemRenderer<DanawaFossilItem> {

    public DanawaFossilItemRenderer() {
        super(new DanawaFossilItemModel());
    }
}
