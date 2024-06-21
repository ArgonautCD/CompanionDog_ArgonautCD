package net.argonaut.companiondog.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.argonaut.companiondog.CompanionDog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class SoulOrbRenderer implements ICurioRenderer {
    private static final ResourceLocation TRANSLUCENT = new ResourceLocation(CompanionDog.MOD_ID, "textures/item/wearable/soul_orb_3d.png");

    private final SoulOrbModel translucent;


    public SoulOrbRenderer() {
        this.translucent = new SoulOrbModel(Minecraft.getInstance().getEntityModels().bakeLayer(SoulOrbModel.HEAD));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();

        this.translucent.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.translucent.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBuffer(renderTypeBuffer, RenderType.entityTranslucent(TRANSLUCENT), false, stack.hasFoil());

        this.translucent.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
