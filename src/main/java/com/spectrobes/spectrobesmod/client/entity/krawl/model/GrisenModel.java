package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGrisen;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GrisenModel extends GeoModel<EntityGrisen> {

	@Override
	public ResourceLocation getModelResource(EntityGrisen object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/gris.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGrisen object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/grisen.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityGrisen object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}