package com.spectrobes.spectrobesmod.common.entities.komainu;

import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.registry.SpectrobePropertyRegistry;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityKomainu extends EntitySpectrobe {

    Spectrobe spectrobeInstance;

    public EntityKomainu(EntityType<EntityKomainu> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn, SpectrobePropertyRegistry.KOMAINU);
    }

    @Override
    protected boolean canEvolve() {
        return false;
    }

    public void setSpectrobeInstance(Spectrobe spectrobe) {
        this.spectrobeInstance = spectrobe;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        //children cant have children, duh.
        return null;
    }
}
