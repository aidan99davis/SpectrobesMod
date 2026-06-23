package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.NaguFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.NaguFossilItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class NaguFossilItemRenderer extends GeoItemRenderer<NaguFossilItem> {

    public NaguFossilItemRenderer() {
        super(new NaguFossilItemModel());
    }
}
