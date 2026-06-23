package com.spectrobes.spectrobesmod.client.items.machines.renderer;

import com.spectrobes.spectrobesmod.client.items.machines.model.HealerBlockItemModel;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HealerBlockItemRenderer extends GeoItemRenderer<HealerBlockItem> {

    public HealerBlockItemRenderer() {
        super(new HealerBlockItemModel());
    }
}
