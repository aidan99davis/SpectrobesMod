package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVizbar;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VizbarModel extends GeoModel<EntityVizbar> {

	@Override
	public ResourceLocation getModelResource(EntityVizbar object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/subar.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityVizbar object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/vizbar.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityVizbar object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/subar.json");
	}
}