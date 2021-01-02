// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VortexModel extends AnimatedGeoModel<EntityVortex> {

	@Override
	public ResourceLocation getModelLocation(EntityVortex object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/vortex.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityVortex object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/vortex.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityVortex object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/vortex.json");
	}
}