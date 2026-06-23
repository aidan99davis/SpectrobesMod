package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.inkana.EntityInkana;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class InkanaModel extends GeoModel<EntityInkana> {

	@Override
	public ResourceLocation getModelResource(EntityInkana object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/inkana.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityInkana object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/inkana_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityInkana object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/inkana.json");
	}
}