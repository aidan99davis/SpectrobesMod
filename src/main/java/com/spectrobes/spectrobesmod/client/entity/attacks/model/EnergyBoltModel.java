package com.spectrobes.spectrobesmod.client.entity.attacks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EnergyBoltModel extends AnimatedGeoModel<EnergyBoltEntity> {

	@Override
	public ResourceLocation getModelResource(EnergyBoltEntity object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/weapons/energy_bolt.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EnergyBoltEntity object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/attack/energy_bolt.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EnergyBoltEntity object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}