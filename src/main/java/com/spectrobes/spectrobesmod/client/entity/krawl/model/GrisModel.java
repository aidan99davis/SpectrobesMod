package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGris;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GrisModel extends GeoModel<EntityGris> {

	@Override
	public ResourceLocation getModelResource(EntityGris object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/gris.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGris object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/gris.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityGris object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}