// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.danawa.EntityDanawa;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DanawaModel extends AnimatedGeoModel<EntityDanawa> {

	@Override
	public ResourceLocation getModelLocation(EntityDanawa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/danawa.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityDanawa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/danawa_0.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityDanawa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/danawa.json");
	}
}