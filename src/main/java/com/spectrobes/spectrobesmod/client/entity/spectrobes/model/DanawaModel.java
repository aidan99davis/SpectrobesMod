package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.danawa.EntityDanawa;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DanawaModel extends GeoModel<EntityDanawa> {

	@Override
	public ResourceLocation getModelResource(EntityDanawa object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/danawa.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityDanawa object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/danawa_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityDanawa object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/danawa.json");
	}
}