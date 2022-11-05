package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kasumi.EntityKasumi;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KasumiModel extends AnimatedGeoModel<EntityKasumi> {

	@Override
	public ResourceLocation getModelResource(EntityKasumi object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/kasumi.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityKasumi object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/kasumi_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityKasumi object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/kasumi.json");
	}
}