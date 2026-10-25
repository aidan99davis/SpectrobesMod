package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponTrackingBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponTrackingBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponTrackingBlasterItemRenderer extends GeoItemRenderer<WeaponTrackingBlasterItem> {
    public WeaponTrackingBlasterItemRenderer() { super(new WeaponTrackingBlasterItemModel()); }
}
