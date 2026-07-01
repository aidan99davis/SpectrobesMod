package com.spectrobes.spectrobesmod.client.entity.attacks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicBlasterEnergyBoltEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

// TODO: This is essentially a duplicate of EnergyBoltModel. Make have different ResourceLocations.
public class BasicBlasterEnergyBoltModel extends GeoModel<BasicBlasterEnergyBoltEntity> {

	@Override
	public ResourceLocation getModelResource(BasicBlasterEnergyBoltEntity object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/energy_bolt.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(BasicBlasterEnergyBoltEntity object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/attack/energy_bolt.png");
	}

	@Override
	public ResourceLocation getAnimationResource(BasicBlasterEnergyBoltEntity object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}