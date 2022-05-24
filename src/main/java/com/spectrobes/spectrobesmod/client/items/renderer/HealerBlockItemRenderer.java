package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.HealerBlockItemModel;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class HealerBlockItemRenderer extends GeoItemRenderer<HealerBlockItem> {

    public HealerBlockItemRenderer() {
        super(new HealerBlockItemModel());
    }
}
