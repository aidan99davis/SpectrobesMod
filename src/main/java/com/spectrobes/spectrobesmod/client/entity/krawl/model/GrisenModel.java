// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGris;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGrisen;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrisenModel extends AnimatedGeoModel<EntityGrisen> {

	@Override
	public ResourceLocation getModelLocation(EntityGrisen object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/gris.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityGrisen object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/grisen.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityGrisen object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}