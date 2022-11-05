package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySubar;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SubarModel extends AnimatedGeoModel<EntitySubar> {

	@Override
	public ResourceLocation getModelResource(EntitySubar object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/subar.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntitySubar object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/subar.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntitySubar object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/subar.json");
	}
}