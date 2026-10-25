package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponBasicBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponBasicBlasterItemRenderer extends GeoItemRenderer<WeaponBasicBlasterItem> {
    public WeaponBasicBlasterItemRenderer() {
        super(new WeaponBasicBlasterItemModel());
    }
}
