package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.dongor.EntityDongor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DongorModel extends GeoModel<EntityDongor> {

	@Override
	public ResourceLocation getModelResource(EntityDongor object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/dongor.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityDongor object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/dongor_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityDongor object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/dongor.json");
	}
}