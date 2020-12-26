package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZozane;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZozaneModel extends AnimatedGeoModel<EntityZozane> {
	@Override
	public ResourceLocation getModelLocation(EntityZozane object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/zozane.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(EntityZozane object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/zozane_0.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(EntityZozane object)
	{
		return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/zozane.json");
	}
}