package net.argonaut.companiondog.entity.client;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.argonaut.companiondog.entity.animations.ModAnimationDefinitions;
import net.argonaut.companiondog.entity.custom.CompanionDogEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


public class GoldenRetriever<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart goldenretriever;
	private final ModelPart head;

	public GoldenRetriever(ModelPart root) {
		this.goldenretriever = root.getChild("goldenretriever");
		this.head = goldenretriever.getChild("cabeza");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition goldenretriever = partdefinition.addOrReplaceChild("goldenretriever", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cuerpo = goldenretriever.addOrReplaceChild("cuerpo", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 2.0F));

		PartDefinition cuello = cuerpo.addOrReplaceChild("cuello", CubeListBuilder.create(), PartPose.offset(1.0F, -5.0F, -11.0F));

		PartDefinition cuello_r1 = cuello.addOrReplaceChild("cuello_r1", CubeListBuilder.create().texOffs(43, 18).addBox(-6.0F, -15.0F, -3.0F, 11.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 2.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cuerpo_p1 = cuerpo.addOrReplaceChild("cuerpo_p1", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -8.0F));

		PartDefinition parte_1_r1 = cuerpo_p1.addOrReplaceChild("parte_1_r1", CubeListBuilder.create().texOffs(0, 25).addBox(-6.0F, -12.0F, -5.0F, 11.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 6.0F, -1.0F, -0.1299F, 0.0F, 0.0F));

		PartDefinition cuerpo_p2 = cuerpo.addOrReplaceChild("cuerpo_p2", CubeListBuilder.create().texOffs(32, 36).addBox(-5.0F, -6.0F, -6.0F, 11.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition cuerpo_p3 = cuerpo.addOrReplaceChild("cuerpo_p3", CubeListBuilder.create().texOffs(10, 10).addBox(-4.0F, -5.0F, -3.0F, 9.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 8.0F));

		PartDefinition cabeza = goldenretriever.addOrReplaceChild("cabeza", CubeListBuilder.create().texOffs(34, 0).addBox(-5.0F, -3.0F, -4.0F, 11.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 46).addBox(-4.0F, -4.0F, -4.0F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -25.0F, -8.0F));

		PartDefinition hocico = cabeza.addOrReplaceChild("hocico", CubeListBuilder.create().texOffs(63, 0).addBox(-2.0F, -24.0F, -16.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 25.0F, 8.0F));

		PartDefinition cube_r1 = hocico.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(70, 61).addBox(-1.0F, -27.0F, -8.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2967F, 0.0F, 0.0F));

		PartDefinition lengua_r1 = hocico.addOrReplaceChild("lengua_r1", CubeListBuilder.create().texOffs(74, 0).addBox(-1.0F, -0.2648F, -0.6532F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, -15.0F, -0.0175F, 0.0F, 0.0F));

		PartDefinition boca_r1 = hocico.addOrReplaceChild("boca_r1", CubeListBuilder.create().texOffs(61, 56).addBox(-2.0F, -0.2132F, -0.7389F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, -15.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition oreja_izquierda = cabeza.addOrReplaceChild("oreja_izquierda", CubeListBuilder.create().texOffs(0, 64).addBox(-0.6127F, -3.809F, -2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(71, 69).addBox(-0.6127F, 2.191F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		PartDefinition oreja_derecha = cabeza.addOrReplaceChild("oreja_derecha", CubeListBuilder.create().texOffs(61, 62).addBox(-2.6455F, -3.301F, -2.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(50, 69).addBox(-2.6455F, 2.699F, -2.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.2356F));

		PartDefinition pata_delantera_izquierda = goldenretriever.addOrReplaceChild("pata_delantera_izquierda", CubeListBuilder.create(), PartPose.offset(-5.0F, -6.0F, -6.0F));

		PartDefinition pata_di_p1 = pata_delantera_izquierda.addOrReplaceChild("pata_di_p1", CubeListBuilder.create().texOffs(50, 56).addBox(0.0F, -4.0F, -3.0F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -5.0F, -1.0F));

		PartDefinition pata_di_p2 = pata_delantera_izquierda.addOrReplaceChild("pata_di_p2", CubeListBuilder.create().texOffs(32, 76).addBox(-2.0F, -4.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 0.0F));

		PartDefinition cube_r2 = pata_di_p2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(50, 78).addBox(-8.0F, -10.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 5.0F, 5.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition pata_di_p3 = pata_delantera_izquierda.addOrReplaceChild("pata_di_p3", CubeListBuilder.create().texOffs(74, 44).addBox(-2.0F, -3.5F, -0.8F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 3.5F, -0.2F));

		PartDefinition pata_di_p4 = pata_delantera_izquierda.addOrReplaceChild("pata_di_p4", CubeListBuilder.create().texOffs(20, 76).addBox(-2.0F, -2.8F, -1.2F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 6.8F, -0.8F));

		PartDefinition pata_delantera_derecha = goldenretriever.addOrReplaceChild("pata_delantera_derecha", CubeListBuilder.create(), PartPose.offset(6.0F, -6.0F, -8.0F));

		PartDefinition pata_dd_p1 = pata_delantera_derecha.addOrReplaceChild("pata_dd_p1", CubeListBuilder.create().texOffs(34, 56).addBox(-2.0F, -4.0F, -3.0F, 3.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition pata_dd_p2 = pata_delantera_derecha.addOrReplaceChild("pata_dd_p2", CubeListBuilder.create().texOffs(10, 76).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition cube_r3 = pata_dd_p2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(42, 76).addBox(-7.0F, -10.0F, -5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 6.0F, 5.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition pata_dd_p3 = pata_delantera_derecha.addOrReplaceChild("pata_dd_p3", CubeListBuilder.create().texOffs(72, 16).addBox(-2.0F, -3.5F, -1.5F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 2.5F));

		PartDefinition pata_dd_p4 = pata_delantera_derecha.addOrReplaceChild("pata_dd_p4", CubeListBuilder.create().texOffs(59, 75).addBox(-1.0F, -1.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 5.0F, 2.0F));

		PartDefinition pata_trasera_izquierda = goldenretriever.addOrReplaceChild("pata_trasera_izquierda", CubeListBuilder.create(), PartPose.offset(-5.0F, -8.0F, 12.0F));

		PartDefinition pata_ti_p1 = pata_trasera_izquierda.addOrReplaceChild("pata_ti_p1", CubeListBuilder.create().texOffs(18, 54).addBox(-1.0F, -4.0F, -2.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -2.0F));

		PartDefinition pata_ti_p2 = pata_trasera_izquierda.addOrReplaceChild("pata_ti_p2", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, 2.0F));

		PartDefinition cube_r4 = pata_ti_p2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(70, 7).addBox(-2.0F, -12.0F, 8.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -4.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r5 = pata_ti_p2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 66).addBox(-2.0F, -14.0F, 6.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -4.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition pata_ti_p3 = pata_trasera_izquierda.addOrReplaceChild("pata_ti_p3", CubeListBuilder.create().texOffs(0, 75).addBox(-1.0F, -3.4F, -0.5F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.4F, 1.5F));

		PartDefinition pata_ti_p4 = pata_trasera_izquierda.addOrReplaceChild("pata_ti_p4", CubeListBuilder.create().texOffs(76, 33).addBox(-2.0F, -1.0F, -2.8F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 7.0F, 1.8F));

		PartDefinition pata_trasera_derecha = goldenretriever.addOrReplaceChild("pata_trasera_derecha", CubeListBuilder.create(), PartPose.offset(6.0F, -9.0F, 12.0F));

		PartDefinition pata_td_p1 = pata_trasera_derecha.addOrReplaceChild("pata_td_p1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -3.0F, 3.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -1.0F));

		PartDefinition pata_td_p2 = pata_trasera_derecha.addOrReplaceChild("pata_td_p2", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 1.0F));

		PartDefinition cube_r6 = pata_td_p2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, 67).addBox(-2.0F, -14.0F, 8.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -3.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r7 = pata_td_p2.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(64, 36).addBox(-2.0F, -15.0F, 6.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -3.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition pata_td_p3 = pata_trasera_derecha.addOrReplaceChild("pata_td_p3", CubeListBuilder.create().texOffs(32, 25).addBox(-2.0F, -3.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 2.0F));

		PartDefinition pata_td_p4 = pata_trasera_derecha.addOrReplaceChild("pata_td_p4", CubeListBuilder.create().texOffs(75, 52).addBox(-2.0F, -1.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 1.0F));

		PartDefinition Cola = goldenretriever.addOrReplaceChild("Cola", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 17.0F));

		PartDefinition Cola_1 = Cola.addOrReplaceChild("Cola_1", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -2.0F));

		PartDefinition pelo_atr_1_r1 = Cola_1.addOrReplaceChild("pelo_atr_1_r1", CubeListBuilder.create().texOffs(8, 86).addBox(0.0F, -8.0F, 14.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, -14.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition parte_1_r2 = Cola_1.addOrReplaceChild("parte_1_r2", CubeListBuilder.create().texOffs(0, 54).addBox(-2.0F, -8.0F, 16.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, -14.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition Cola_2 = Cola.addOrReplaceChild("Cola_2", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition pelo_art_2_r1 = Cola_2.addOrReplaceChild("pelo_art_2_r1", CubeListBuilder.create().texOffs(3, 86).addBox(0.0F, -5.0F, 14.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -16.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition parte_2_r1 = Cola_2.addOrReplaceChild("parte_2_r1", CubeListBuilder.create().texOffs(26, 67).addBox(-1.0F, -5.0F, 16.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 12.0F, -16.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition Cola_3 = Cola.addOrReplaceChild("Cola_3", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 1.0F));

		PartDefinition pelo_atr_3_r1 = Cola_3.addOrReplaceChild("pelo_atr_3_r1", CubeListBuilder.create().texOffs(1, 87).addBox(0.5F, 0.0F, 15.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 25).addBox(-1.0F, 0.0F, 16.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -17.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition Cola_4 = Cola.addOrReplaceChild("Cola_4", CubeListBuilder.create(), PartPose.offset(0.0F, 7.8F, 2.5F));

		PartDefinition Cola_4_r1 = Cola_4.addOrReplaceChild("Cola_4_r1", CubeListBuilder.create().texOffs(18, 88).addBox(-0.5F, -1.2167F, -1.2565F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.2F, -0.5F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animate(((CompanionDogEntity)entity).idleAnimationState, ModAnimationDefinitions.idle, ageInTicks, 1f);
		this.animateWalk(ModAnimationDefinitions.jogging, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((CompanionDogEntity)entity).sitAnimationState, ModAnimationDefinitions.sit, ageInTicks);
		this.animate(((CompanionDogEntity)entity).standAnimationState,ModAnimationDefinitions.stand_up, ageInTicks);
	}
	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks){
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		goldenretriever.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return goldenretriever;
	}
}