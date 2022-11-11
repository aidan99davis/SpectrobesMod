package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityXelles;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class XellesModel extends AnimatedGeoModel<EntityXelles> {

	@Override
	public ResourceLocation getModelResource(EntityXelles object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityXelles object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/xelles.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityXelles object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/xelles.json");
	}
}