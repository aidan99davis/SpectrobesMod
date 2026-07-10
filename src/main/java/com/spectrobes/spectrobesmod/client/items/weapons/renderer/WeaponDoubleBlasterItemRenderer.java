package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponBasicBlasterItemModel;
import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponDoubleBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterItem;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponDoubleBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponDoubleBlasterItemRenderer extends GeoItemRenderer<WeaponDoubleBlasterItem> {
    public WeaponDoubleBlasterItemRenderer() {
        super(new WeaponDoubleBlasterItemModel());
    }
}
