package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponTripleBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponTripleBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponTripleBlasterItemRenderer extends GeoItemRenderer<WeaponTripleBlasterItem> {
    public WeaponTripleBlasterItemRenderer() { super(new WeaponTripleBlasterItemModel()); }
}
