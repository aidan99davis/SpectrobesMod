package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumite;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HarumiteModel extends GeoModel<EntityHarumite> {
	@Override
	public ResourceLocation getModelResource(EntityHarumite object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/harumite.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityHarumite object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumite_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityHarumite object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/harumite.json");
	}
}