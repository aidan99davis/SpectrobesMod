package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu.EntityNaguryu;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NaguryuModel extends GeoModel<EntityNaguryu> {

	@Override
	public ResourceLocation getModelResource(EntityNaguryu object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/naguryu.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityNaguryu object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/naguryu_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityNaguryu object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/naguryu.json");
	}
}