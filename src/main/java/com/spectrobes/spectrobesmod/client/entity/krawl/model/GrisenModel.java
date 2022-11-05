package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGrisen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrisenModel extends AnimatedGeoModel<EntityGrisen> {

	@Override
	public ResourceLocation getModelResource(EntityGrisen object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/gris.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGrisen object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/grisen.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityGrisen object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}