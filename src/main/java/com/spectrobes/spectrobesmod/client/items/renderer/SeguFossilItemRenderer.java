package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.SeguFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.SeguFossilItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class SeguFossilItemRenderer extends GeoItemRenderer<SeguFossilItem> {

    public SeguFossilItemRenderer() {
        super(new SeguFossilItemModel());
    }
}
