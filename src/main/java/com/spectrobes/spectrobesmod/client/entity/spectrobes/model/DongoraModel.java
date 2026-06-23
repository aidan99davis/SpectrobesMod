package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.dongor.EntityDongora;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DongoraModel extends GeoModel<EntityDongora> {

	@Override
	public ResourceLocation getModelResource(EntityDongora object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/dongora.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityDongora object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/dongora_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityDongora object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/dongora.json");
	}
}