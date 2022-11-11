package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari.EntityMossari;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MossariModel extends AnimatedGeoModel<EntityMossari> {

	@Override
	public ResourceLocation getModelResource(EntityMossari object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/mossari.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityMossari object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mossari_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityMossari object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/mossari.json");
	}
}