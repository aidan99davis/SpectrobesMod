package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.inkana.EntityInkana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class InkanaModel extends AnimatedGeoModel<EntityInkana> {

	@Override
	public ResourceLocation getModelResource(EntityInkana object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/inkana.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityInkana object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/inkana_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityInkana object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/inkana.json");
	}
}