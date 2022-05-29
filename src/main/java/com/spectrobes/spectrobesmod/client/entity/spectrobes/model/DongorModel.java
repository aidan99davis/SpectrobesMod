// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.dongor.EntityDongor;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DongorModel extends AnimatedGeoModel<EntityDongor> {

	@Override
	public ResourceLocation getModelLocation(EntityDongor object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/dongor.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityDongor object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/dongor_0.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityDongor object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/dongor.json");
	}
}