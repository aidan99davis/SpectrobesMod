package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VortexModel extends GeoModel<EntityVortex> {

	@Override
	public ResourceLocation getModelResource(EntityVortex object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/vortex.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityVortex object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/vortex.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityVortex object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/vortex.json");
	}
}