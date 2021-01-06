package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.MossariFossilItemModel;
import com.spectrobes.spectrobesmod.client.items.model.NaguFossilItemModel;
import com.spectrobes.spectrobesmod.common.items.fossils.MossariFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.NaguFossilItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class MossariFossilItemRenderer extends GeoItemRenderer<MossariFossilItem> {

    public MossariFossilItemRenderer() {
        super(new MossariFossilItemModel());
    }
}
