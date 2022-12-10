package com.spectrobes.spectrobesmod.client.items.machines.renderer;

import com.spectrobes.spectrobesmod.client.items.machines.model.HealerBlockItemModel;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class HealerBlockItemRenderer extends GeoItemRenderer<HealerBlockItem> {

    public HealerBlockItemRenderer() {
        super(new HealerBlockItemModel());
    }
}
