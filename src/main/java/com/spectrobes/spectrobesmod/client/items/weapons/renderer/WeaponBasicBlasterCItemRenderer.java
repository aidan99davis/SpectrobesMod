package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponBasicBlasterCItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterCItem;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WeaponBasicBlasterCItemRenderer extends GeoItemRenderer<WeaponBasicBlasterCItem> {
    public WeaponBasicBlasterCItemRenderer() {
        super(new WeaponBasicBlasterCItemModel());
    }
}
