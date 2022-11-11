package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZoza;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZozaModel extends AnimatedGeoModel<EntityZoza> {
	@Override
	public ResourceLocation getModelResource(EntityZoza object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/zoza.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityZoza object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/zoza_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityZoza object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/zoza.json");
	}
}