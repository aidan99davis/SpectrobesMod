package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.masetto.EntityMasetto;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MasettoModel extends AnimatedGeoModel<EntityMasetto> {

	@Override
	public ResourceLocation getModelResource(EntityMasetto object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/masetto.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityMasetto object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/masetto_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityMasetto object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/masetto.json");
	}
}