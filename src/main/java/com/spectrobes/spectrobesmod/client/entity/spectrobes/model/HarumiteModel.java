package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumite;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HarumiteModel extends AnimatedGeoModel<EntityHarumite> {
	@Override
	public ResourceLocation getModelResource(EntityHarumite object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/harumite.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityHarumite object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumite_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityHarumite object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/harumite.json");
	}
}