// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.aoi.EntityAoi;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mesa.EntityMesa;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MesaModel extends AnimatedGeoModel<EntityMesa> {

	@Override
	public ResourceLocation getModelLocation(EntityMesa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/mesa.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityMesa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mesa_0.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityMesa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/mesa.json");
	}
}