package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponHyperBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponHyperBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponHyperBlasterItemRenderer extends GeoItemRenderer<WeaponHyperBlasterItem> {
    public WeaponHyperBlasterItemRenderer() {
        super(new WeaponHyperBlasterItemModel());
    }
}
