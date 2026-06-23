package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.tenkro.EntityTenkro;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TenkroModel extends GeoModel<EntityTenkro> {

	@Override
	public ResourceLocation getModelResource(EntityTenkro object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/tenkro.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityTenkro object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/tenkro_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityTenkro object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/tenkro.json");
	}
}