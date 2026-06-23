package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZoza;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ZozaModel extends GeoModel<EntityZoza> {
	@Override
	public ResourceLocation getModelResource(EntityZoza object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/zoza.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityZoza object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/zoza_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityZoza object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/zoza.json");
	}
}