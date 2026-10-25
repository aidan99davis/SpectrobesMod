package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponPhotonBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponPhotonBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponPhotonBlasterItemRenderer extends GeoItemRenderer<WeaponPhotonBlasterItem> {
    public WeaponPhotonBlasterItemRenderer() { super(new WeaponPhotonBlasterItemModel()); }
}
