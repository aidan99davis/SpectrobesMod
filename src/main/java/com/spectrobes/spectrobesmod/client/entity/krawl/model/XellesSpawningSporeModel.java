package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySpawningSpore;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class XellesSpawningSporeModel extends AnimatedGeoModel<EntitySpawningSpore> {

	@Override
	public ResourceLocation getModelResource(EntitySpawningSpore object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles_spore_spawn.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntitySpawningSpore object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/xelles_spore_spawn.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntitySpawningSpore object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/xelles_spore_spawn.json");
	}
}