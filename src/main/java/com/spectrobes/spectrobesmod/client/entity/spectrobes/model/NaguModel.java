package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu.EntityNagu;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NaguModel extends GeoModel<EntityNagu> {

	@Override
	public ResourceLocation getModelResource(EntityNagu object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/nagu.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityNagu object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/nagu_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityNagu object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/nagu.json");
	}
}