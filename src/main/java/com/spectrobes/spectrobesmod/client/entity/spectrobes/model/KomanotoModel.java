package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomanoto;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KomanotoModel extends GeoModel<EntityKomanoto> {

	@Override
	public ResourceLocation getModelResource(EntityKomanoto object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/komanoto.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityKomanoto object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/komanoto_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityKomanoto object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/komanoto.json");
	}
}