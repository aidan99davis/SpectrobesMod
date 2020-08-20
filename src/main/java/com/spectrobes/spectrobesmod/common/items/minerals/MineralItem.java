package com.spectrobes.spectrobesmod.common.items.minerals;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class MineralItem extends Item {
    public MineralProperties mineralProperties = null;

    public MineralItem(Properties properties, String registryName, MineralProperties mineralProperties) {
        super(properties);
        setRegistryName(registryName);
        this.mineralProperties = mineralProperties;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if(mineralProperties.getNature() != SpectrobeProperties.Nature.OTHER) {
            switch(mineralProperties.getNature()) {
                case CORONA:
                    tooltip.add(new StringTextComponent("\u00A74" + "Corona" + "\u00A74"));
                    break;
                case AURORA:
                    tooltip.add(new StringTextComponent("\u00A72" + "Aurora" + "\u00A72"));
                    break;
                case FLASH:
                    tooltip.add(new StringTextComponent("\u00A71" + "Flash" + "\u00A71"));
                    break;
                    default:break;
            }
        }
        if(mineralProperties.getHpOffset() != 0)
            tooltip.add(new StringTextComponent("Hp: " + mineralProperties.getHpOffset()));
        if(mineralProperties.getAtkOffset()!= 0)
            tooltip.add(new StringTextComponent("Atk: " + mineralProperties.getAtkOffset()));
        if(mineralProperties.getDefOffset() != 0)
            tooltip.add(new StringTextComponent("Def: " + mineralProperties.getDefOffset()));

        tooltip.add(new StringTextComponent("Xp: " + mineralProperties.getXpWorth()));
    }
}
