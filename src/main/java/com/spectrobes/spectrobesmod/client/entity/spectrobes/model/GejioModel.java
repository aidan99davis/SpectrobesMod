package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.gejio.EntityGejio;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GejioModel extends GeoModel<EntityGejio> {

	@Override
	public ResourceLocation getModelResource(EntityGejio object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/gejio.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGejio object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/gejio_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityGejio object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/gejio.json");
	}
}