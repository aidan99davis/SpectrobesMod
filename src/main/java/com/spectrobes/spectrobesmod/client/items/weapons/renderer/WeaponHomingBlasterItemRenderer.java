package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponHomingBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponHomingBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponHomingBlasterItemRenderer extends GeoItemRenderer<WeaponHomingBlasterItem> {
    public WeaponHomingBlasterItemRenderer() { super(new WeaponHomingBlasterItemModel()); }
}
