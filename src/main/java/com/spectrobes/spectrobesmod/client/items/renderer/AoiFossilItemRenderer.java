package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.AoiFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.AoiFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class AoiFossilItemRenderer extends GeoItemRenderer<AoiFossilItem> {

    public AoiFossilItemRenderer() {
        super(new AoiFossilItemModel());
    }
}
