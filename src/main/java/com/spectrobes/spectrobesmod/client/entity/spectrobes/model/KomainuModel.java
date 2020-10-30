package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class KomainuModel extends AnimatedGeoModel<EntityKomainu> {

	@Override
	public ResourceLocation getModelLocation(EntityKomainu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/komainu.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityKomainu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/komainu_0.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityKomainu object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/komainu.json");
	}
}