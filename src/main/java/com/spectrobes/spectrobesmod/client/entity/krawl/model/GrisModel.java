package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrisModel extends AnimatedGeoModel<EntityGris> {

	@Override
	public ResourceLocation getModelResource(EntityGris object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/gris.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGris object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/gris.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityGris object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}