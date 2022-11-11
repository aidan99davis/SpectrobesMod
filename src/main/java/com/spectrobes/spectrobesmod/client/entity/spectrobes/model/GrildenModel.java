package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilden;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrildenModel extends AnimatedGeoModel<EntityGrilden> {

	@Override
	public ResourceLocation getModelResource(EntityGrilden object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/grilden.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGrilden object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/grilden_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityGrilden object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/grilden.json");
	}
}