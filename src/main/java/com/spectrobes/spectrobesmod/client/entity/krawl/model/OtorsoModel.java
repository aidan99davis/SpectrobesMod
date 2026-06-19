package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityOtorso;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OtorsoModel extends GeoModel<EntityOtorso> {

	@Override
	public ResourceLocation getModelResource(EntityOtorso object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/otorso.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityOtorso object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/otorso.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityOtorso object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/otorso.json");
	}
}