package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponRayBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponRayBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponRayBlasterItemRenderer extends GeoItemRenderer<WeaponRayBlasterItem> {
    public WeaponRayBlasterItemRenderer() {
        super(new WeaponRayBlasterItemModel());
    }
}
