// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilden;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrildenModel extends AnimatedGeoModel<EntityGrilden> {

	@Override
	public ResourceLocation getModelLocation(EntityGrilden object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/grilden.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityGrilden object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/grilden_0.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityGrilden object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/grilden.json");
	}
}