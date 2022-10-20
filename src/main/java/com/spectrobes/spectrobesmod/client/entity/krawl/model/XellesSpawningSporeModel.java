// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityHealingSpore;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySpawningSpore;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class XellesSpawningSporeModel extends AnimatedGeoModel<EntitySpawningSpore> {

	@Override
	public ResourceLocation getModelLocation(EntitySpawningSpore object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles_spore_spawn.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntitySpawningSpore object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/xelles_spore_spawn.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntitySpawningSpore object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/xelles_spore_spawn.json");
	}
}