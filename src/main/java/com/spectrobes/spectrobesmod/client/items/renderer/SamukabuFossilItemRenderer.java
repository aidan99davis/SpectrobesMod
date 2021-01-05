package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.NaguFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.SamukabuFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.NaguFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.SamukabuFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class SamukabuFossilItemRenderer extends GeoItemRenderer<SamukabuFossilItem> {

    public SamukabuFossilItemRenderer() {
        super(new SamukabuFossilItemModel());
    }
}
