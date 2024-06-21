package net.argonaut.companiondog.entity.client;

import com.google.common.collect.ImmutableList;

import net.argonaut.companiondog.CompanionDog;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class SoulOrbModel extends HumanoidModel<LivingEntity> {
    public static final ModelLayerLocation HEAD = new ModelLayerLocation(new ResourceLocation(CompanionDog.MOD_ID, "soul_orb_3d"), "soul_orb_3d");
    public ModelPart head;


    public SoulOrbModel(ModelPart pRoot) {
        super(pRoot);
        this.head = pRoot.getChild("soul_orb_3d");
    }

    public static LayerDefinition createLayer(){
        CubeDeformation cube = new CubeDeformation(0.0F);
        MeshDefinition mesh = HumanoidModel.createMesh(cube, 0.0F);
        PartDefinition pRoot = mesh.getRoot();
        pRoot.addOrReplaceChild("soul_orb_3d", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-2.0F, -27.0F, -2.0F, 4.0F, 4.0F, 4.0F, cube).
                        texOffs(0,9).addBox(-1.0F, -26.0F, -1.0F, 2.0F,2.0F,2.0F, cube),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 16, 16);
    }
    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
        float orbitRadius = 12.0f; //Radio
        float orbitSpeed = 0.05f;  //Velocidad de rotación
        float orbitAngle = pAgeInTicks * orbitSpeed;
        float x = (float) Math.cos(orbitAngle) * orbitRadius;
        float z = (float) Math.sin(orbitAngle) * orbitRadius;
        this.head.setPos(x, 0.0F, z);

        float selfRotationSpeed = 0.05f; //Velocidad rotación sobre si mismo
        this.head.yRot = (pAgeInTicks * selfRotationSpeed) % 360;

        float verticalOffset = 1.0f; //Amplitud
        float verticalFrequency = 0.1f; //Frecuencia
        float verticalMovement = (float) Math.sin(pAgeInTicks * verticalFrequency) * verticalOffset;
        this.head.y += verticalMovement;
    }



    @Override
    @Nonnull
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    @Nonnull
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of();
    }
}