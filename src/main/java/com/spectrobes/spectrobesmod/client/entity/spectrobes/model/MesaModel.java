package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mesa.EntityMesa;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MesaModel extends AnimatedGeoModel<EntityMesa> {

	@Override
	public ResourceLocation getModelResource(EntityMesa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/mesa.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityMesa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mesa_0.png");
	}

	@Override
	public ResourceLocation getAnimationResource(EntityMesa object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/mesa.json");
	}
}