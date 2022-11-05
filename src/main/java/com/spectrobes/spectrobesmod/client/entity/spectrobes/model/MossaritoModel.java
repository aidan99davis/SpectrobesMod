package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari.EntityMossarito;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MossaritoModel extends AnimatedGeoModel<EntityMossarito> {

	@Override
	public ResourceLocation getModelResource(EntityMossarito object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/mossarito.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityMossarito object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mossarito_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityMossarito object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/mossarito.json");
	}
}