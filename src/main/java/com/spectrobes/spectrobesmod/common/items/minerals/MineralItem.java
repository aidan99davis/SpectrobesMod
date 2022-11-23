package com.spectrobes.spectrobesmod.common.items.minerals;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MineralItem extends Item implements IWorthGura {
    public Mineral mineral;

    public MineralItem(Mineral mineral) {
        super(new Item.Properties()
                .tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance));
        this.mineral = mineral;

        List<Item> list = SpectrobesMineralsRegistry.all_minerals.get(mineral.rarity);
        list.add(this);
        SpectrobesMineralsRegistry.all_minerals.put(mineral.rarity, list);
    }

    public int getGuraWorth() {
        return mineral.properties.getGuraWorth();
    }

    @Override
    public String getName() {
        return mineral.name;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flag) {
        MineralProperties mineralProperties = mineral.properties;
        if(mineralProperties.getNature() != SpectrobeProperties.Nature.OTHER) {
            switch(mineralProperties.getNature()) {
                case CORONA:
                    tooltip.add(Component.literal("\u00A74" + "Corona" + "\u00A74"));
                    break;
                case AURORA:
                    tooltip.add(Component.literal("\u00A72" + "Aurora" + "\u00A72"));
                    break;
                case FLASH:
                    tooltip.add(Component.literal("\u00A71" + "Flash" + "\u00A71"));
                    break;
                    default:break;
            }
        }
        if(mineralProperties.getHpOffset() != 0)
            tooltip.add(Component.literal("Hp: " + mineralProperties.getHpOffset()));
        if(mineralProperties.getAtkOffset()!= 0)
            tooltip.add(Component.literal("Atk: " + mineralProperties.getAtkOffset()));
        if(mineralProperties.getDefOffset() != 0)
            tooltip.add(Component.literal("Def: " + mineralProperties.getDefOffset()));

        tooltip.add(Component.literal("Xp: " + mineralProperties.getXpWorth()));
    }
}
