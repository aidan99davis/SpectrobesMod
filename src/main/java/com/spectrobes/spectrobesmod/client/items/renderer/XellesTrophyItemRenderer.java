package com.spectrobes.spectrobesmod.client.items.renderer;

import com.spectrobes.spectrobesmod.client.items.model.XellesTrophyItemModel;
import com.spectrobes.spectrobesmod.common.items.special.XellesTrophyItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class XellesTrophyItemRenderer extends GeoItemRenderer<XellesTrophyItem> {

    public XellesTrophyItemRenderer() {
        super(new XellesTrophyItemModel());
    }
}
