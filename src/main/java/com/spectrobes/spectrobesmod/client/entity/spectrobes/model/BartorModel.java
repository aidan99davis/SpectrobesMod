package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.bartor.EntityBartor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BartorModel extends AnimatedGeoModel<EntityBartor> {

	@Override
	public ResourceLocation getModelResource(EntityBartor object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/bartor.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityBartor object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/bartor_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityBartor object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/bartor.json");
	}
}