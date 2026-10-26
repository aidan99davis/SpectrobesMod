package com.spectrobes.spectrobesmod.client.items.machines.renderer;

import com.spectrobes.spectrobesmod.client.items.machines.model.LabSystemBlockItemModel;
import com.spectrobes.spectrobesmod.common.items.machines.LabSystemBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LabSystemBlockItemRenderer extends GeoItemRenderer<LabSystemBlockItem> {

    public LabSystemBlockItemRenderer() {
        super(new LabSystemBlockItemModel());
    }
}
