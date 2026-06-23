package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumi;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HarumiModel extends GeoModel<EntityHarumi> {
	@Override
	public ResourceLocation getModelResource(EntityHarumi object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/harumi.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityHarumi object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumi_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityHarumi object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/harumi.json");
	}
}