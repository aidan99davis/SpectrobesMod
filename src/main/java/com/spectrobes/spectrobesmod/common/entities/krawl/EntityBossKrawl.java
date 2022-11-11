package com.spectrobes.spectrobesmod.common.entities.krawl;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import static net.minecraft.world.BossEvent.BossBarOverlay.PROGRESS;

public abstract class EntityBossKrawl extends EntityKrawl {
    private final ServerBossEvent bossEvent;

    public EntityBossKrawl(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        this.bossEvent = ((ServerBossEvent)new ServerBossEvent(this.getDisplayName(), getBossNameColour(), PROGRESS).setDarkenScreen(false));
    }

    public abstract BossEvent.BossBarColor getBossNameColour();

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (this.hasCustomName()) {
            this.bossEvent.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(@org.jetbrains.annotations.Nullable net.minecraft.network.chat.Component pName) {
        super.setCustomName(pName);
        this.bossEvent.setName(this.getDisplayName());
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void tick() {
        super.tick();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
