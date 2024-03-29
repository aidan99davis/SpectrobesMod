package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomanoto;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KomanotoModel extends AnimatedGeoModel<EntityKomanoto> {

	@Override
	public ResourceLocation getModelResource(EntityKomanoto object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/komanoto.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityKomanoto object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/komanoto_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityKomanoto object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/komanoto.json");
	}
}