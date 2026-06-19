package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.aoi.EntityAoi;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class AoiModel extends GeoModel<EntityAoi> {

	@Override
	public ResourceLocation getModelResource(EntityAoi object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/aoi.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityAoi object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/aoi_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityAoi object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/aoi.json");
	}
}