package net.argonaut.companiondog.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.argonaut.companiondog.CompanionDog;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CompanionDogRenderer extends MobRenderer<CompanionDogEntity, GoldenRetriever<CompanionDogEntity>> {

    public CompanionDogRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GoldenRetriever<>(pContext.bakeLayer(ModModelLayers.COMPANIONDOG_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(CompanionDogEntity companionDogEntity) {
        return new ResourceLocation(CompanionDog.MOD_ID, "textures/entity/companiondog_texture.png");
    }

    @Override
    public void render(CompanionDogEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.scale(0.6f, 0.6f, 0.6f);

        if (pEntity.isBaby()){
            pPoseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
