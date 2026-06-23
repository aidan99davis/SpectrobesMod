package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.bartor.EntityBartolor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BartolorModel extends GeoModel<EntityBartolor> {

	@Override
	public ResourceLocation getModelResource(EntityBartolor object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/bartolor.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityBartolor object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/bartolor_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityBartolor object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/bartolor.json");
	}
}