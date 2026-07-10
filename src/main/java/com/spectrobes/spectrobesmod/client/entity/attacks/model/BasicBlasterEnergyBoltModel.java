package com.spectrobes.spectrobesmod.client.entity.attacks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.attacks.AbstractEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicEnergyBoltEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

// TODO: This is essentially a duplicate of EnergyBoltModel. Make have different ResourceLocations.
public class BasicBlasterEnergyBoltModel extends GeoModel<AbstractEnergyBoltEntity> {

	@Override
	public ResourceLocation getModelResource(AbstractEnergyBoltEntity object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/energy_bolt.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AbstractEnergyBoltEntity object)
	{
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/attack/energy_bolt.png");
	}

	@Override
	public ResourceLocation getAnimationResource(AbstractEnergyBoltEntity object)
	{
        // TODO: This seems like something that needs to be replaced.
		return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/krawl/gris.json");
	}
}