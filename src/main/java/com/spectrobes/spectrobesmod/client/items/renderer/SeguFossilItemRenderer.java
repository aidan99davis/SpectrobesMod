package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.SamukabuFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.SeguFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.SamukabuFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.SeguFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class SeguFossilItemRenderer extends GeoItemRenderer<SeguFossilItem> {

    public SeguFossilItemRenderer() {
        super(new SeguFossilItemModel());
    }
}
