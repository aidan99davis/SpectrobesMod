package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.danawa.EntityDanawa;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DanawaModel extends AnimatedGeoModel<EntityDanawa> {

	@Override
	public ResourceLocation getModelResource(EntityDanawa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/danawa.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityDanawa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/danawa_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityDanawa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/danawa.json");
	}
}