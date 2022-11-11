package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KomainuModel extends AnimatedGeoModel<EntityKomainu> {

	@Override
	public ResourceLocation getModelResource(EntityKomainu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/komainu.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityKomainu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/komainu_1.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityKomainu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/komainu.json");
	}
}