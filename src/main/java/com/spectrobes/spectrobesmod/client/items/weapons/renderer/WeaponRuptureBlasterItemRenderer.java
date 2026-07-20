package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponRuptureBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponRuptureBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponRuptureBlasterItemRenderer extends GeoItemRenderer<WeaponRuptureBlasterItem> {
    public WeaponRuptureBlasterItemRenderer() { super(new WeaponRuptureBlasterItemModel()); }
}
