package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu.EntityNagu;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NaguModel extends AnimatedGeoModel<EntityNagu> {

	@Override
	public ResourceLocation getModelResource(EntityNagu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/nagu.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityNagu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/nagu_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityNagu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/nagu.json");
	}
}