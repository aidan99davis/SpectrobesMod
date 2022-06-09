package com.spectrobes.spectrobesmod.common.entities.krawl;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

import javax.annotation.Nullable;

public abstract class EntityBossKrawl extends EntityKrawl {
    private final ServerBossInfo bossEvent;

    public EntityBossKrawl(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.bossEvent = (ServerBossInfo)(new ServerBossInfo(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenScreen(false);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }

    }

    @Override
    public void setCustomName(@Nullable ITextComponent pName) {
        super.setCustomName(pName);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void startSeenByPlayer(ServerPlayerEntity pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayerEntity pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setPercent(this.getHealth() / this.getMaxHealth());
    }
}
