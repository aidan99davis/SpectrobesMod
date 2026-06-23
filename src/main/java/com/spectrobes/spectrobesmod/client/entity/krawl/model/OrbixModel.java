package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityOrbix;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrbixModel extends GeoModel<EntityOrbix> {

	@Override
	public ResourceLocation getModelResource(EntityOrbix object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/orbix.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityOrbix object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/orbix.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityOrbix object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/orbix.json");
	}
}