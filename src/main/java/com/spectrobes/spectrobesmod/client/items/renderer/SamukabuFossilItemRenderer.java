package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.SamukabuFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.SamukabuFossilItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class SamukabuFossilItemRenderer extends GeoItemRenderer<SamukabuFossilItem> {

    public SamukabuFossilItemRenderer() {
        super(new SamukabuFossilItemModel());
    }
}
