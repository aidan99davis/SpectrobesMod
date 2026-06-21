package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MossariFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.MossariFossilItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class MossariFossilItemRenderer extends GeoItemRenderer<MossariFossilItem> {

    public MossariFossilItemRenderer() {
        super(new MossariFossilItemModel());
    }
}
