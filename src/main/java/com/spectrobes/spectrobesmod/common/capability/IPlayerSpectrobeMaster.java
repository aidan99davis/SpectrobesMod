package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AutoRegisterCapability
public interface IPlayerSpectrobeMaster extends INBTSerializable<CompoundTag> {
    Spectrobe getCurrentTeamMember();
    void changeSelected(int direction);
    int usedTeamSlots();
    double averageBattleTeamLevel();
    void addSpectrobe(Spectrobe spectrobeInstance);
    void setTeamMember(int index, UUID spectrobeUUID);
    void removeTeamMember(int index);
    List<Spectrobe> getOwnedSpectrobes();
    int getOwnedSpectrobesCount();
    Map<Integer, UUID> getCurrentTeamUuids();
    void updateSpectrobe(Spectrobe spectrobeInstance);
    void setSpectrobeInactive(Spectrobe spectrobeData);
    void spawnSpectrobe(Spectrobe spectrobeData);
    void releaseSpectrobe(Spectrobe spectrobe);
    void spawnCurrent();
    Spectrobe getSpectrobeByUuid(UUID uuid);
    int getCurrentTeamMemberSlot();
    int getCurrentGuraBalance();
    void addGura(int gura);
    boolean spendGura(int gura);
    int getCurrentHealth();
    void setCurrentHealth(int currentHealth);
    int getMaxHealth();
    void setMaxHealth(int maxHealth);
    int getLevel();
    void setLevel(int level);
    int getCurrentXp();
    void addXp(int currentXp);
    int getXp_required();
    void setXp_required(int xp_required);
    void copyFrom(PlayerSpectrobeMaster oldStore);
}
