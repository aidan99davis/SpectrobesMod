package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.tenkro.EntityTenkro;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TenkroModel extends AnimatedGeoModel<EntityTenkro> {

	@Override
	public ResourceLocation getModelResource(EntityTenkro object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/tenkro.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityTenkro object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/tenkro_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityTenkro object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/tenkro.json");
	}
}