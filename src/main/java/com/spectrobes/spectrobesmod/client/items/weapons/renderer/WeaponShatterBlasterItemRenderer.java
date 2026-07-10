package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponShatterBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponShatterBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponShatterBlasterItemRenderer extends GeoItemRenderer<WeaponShatterBlasterItem> {
    public WeaponShatterBlasterItemRenderer() {
        super(new WeaponShatterBlasterItemModel());
    }
}
