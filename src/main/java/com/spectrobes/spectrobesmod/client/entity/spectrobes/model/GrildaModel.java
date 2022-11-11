package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilda;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrildaModel extends AnimatedGeoModel<EntityGrilda> {

	@Override
	public ResourceLocation getModelResource(EntityGrilda object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/grilda.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGrilda object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/grilda_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityGrilda object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/grilda.json");
	}
}