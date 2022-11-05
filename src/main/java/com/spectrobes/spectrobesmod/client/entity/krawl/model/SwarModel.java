package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SwarModel extends AnimatedGeoModel<EntitySwar> {

	@Override
	public ResourceLocation getModelResource(EntitySwar object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/swar.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntitySwar object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/swar.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntitySwar object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/swar.json");
	}
}