package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.WeaponBasicBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.renderer.GeoItemRenderer;

@OnlyIn(Dist.CLIENT)
public class WeaponBasicBlasterItemRenderer extends GeoItemRenderer<WeaponBasicBlasterItem> {

    public WeaponBasicBlasterItemRenderer() {
        super(new WeaponBasicBlasterItemModel());
    }
}
