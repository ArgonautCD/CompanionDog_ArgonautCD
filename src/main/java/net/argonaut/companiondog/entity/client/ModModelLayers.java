package net.argonaut.companiondog.entity.client;

import net.argonaut.companiondog.CompanionDog;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation COMPANIONDOG_LAYER = new ModelLayerLocation(
            new ResourceLocation(CompanionDog.MOD_ID, "companiondog_layer"), "main");
    public static final ModelLayerLocation SOUL_ORB_LAYER = new ModelLayerLocation(
            new ResourceLocation(CompanionDog.MOD_ID, "soul_orb_3d"), "soul_orb_3d");
}
