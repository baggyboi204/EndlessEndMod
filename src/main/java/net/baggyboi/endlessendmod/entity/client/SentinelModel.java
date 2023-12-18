package net.baggyboi.endlessendmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.baggyboi.endlessendmod.entity.animations.ModAnimationDefinitions;
import net.baggyboi.endlessendmod.entity.custom.SentinelEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SentinelModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart sentinel;

    public SentinelModel(ModelPart root) {
        this.sentinel = root.getChild("sentinel");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition sentinel = partdefinition.addOrReplaceChild("sentinel", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition core = sentinel.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, 0.0F));

        PartDefinition eyes = core.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 0.0F));

        PartDefinition front_eye = eyes.addOrReplaceChild("front_eye", CubeListBuilder.create().texOffs(40, 14).addBox(-3.0F, -4.0F, -5.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -24.0F, 0.0F));

        PartDefinition back_eye = eyes.addOrReplaceChild("back_eye", CubeListBuilder.create().texOffs(32, 27).addBox(-2.0F, -11.0F, 6.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -17.0F, 0.0F));

        PartDefinition left_eye = eyes.addOrReplaceChild("left_eye", CubeListBuilder.create(), PartPose.offset(1.0F, -24.0F, 0.0F));

        PartDefinition cube_r1 = left_eye.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 32).addBox(-3.0F, -4.0F, 4.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition right_eye = eyes.addOrReplaceChild("right_eye", CubeListBuilder.create(), PartPose.offset(1.0F, -24.0F, 0.0F));

        PartDefinition cube_r2 = right_eye.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 39).addBox(-3.0F, -4.0F, -7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -14.0F, -4.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front = body.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_plate = front.addOrReplaceChild("front_plate", CubeListBuilder.create().texOffs(28, 47).addBox(-4.0F, 0.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 7).addBox(-6.0F, -10.0F, -5.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 47).addBox(1.0F, 0.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 0).addBox(1.0F, -10.0F, -5.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 31).addBox(-3.0F, -8.0F, -5.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(7, 44).addBox(-3.0F, 1.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -7.0F, 0.0F));

        PartDefinition front_casing = front.addOrReplaceChild("front_casing", CubeListBuilder.create().texOffs(39, 45).addBox(-6.0F, -6.0F, -6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(5.0F, -6.0F, -6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(22, 24).addBox(-5.0F, -4.0F, -6.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back = body.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(1.0F, -7.0F, 0.0F));

        PartDefinition back_plate = back.addOrReplaceChild("back_plate", CubeListBuilder.create().texOffs(30, 0).addBox(-7.0F, -10.0F, 6.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 26).addBox(-4.0F, 0.0F, 6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(1.0F, 0.0F, 6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(1.0F, -10.0F, 6.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 37).addBox(-3.0F, -8.0F, 6.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 7).addBox(-3.0F, 1.0F, 6.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back_casing = back.addOrReplaceChild("back_casing", CubeListBuilder.create().texOffs(33, 44).addBox(-6.0F, -6.0F, 6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(27, 42).addBox(5.0F, -6.0F, 6.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-5.0F, -4.0F, 6.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, 0.0F));

        PartDefinition left = body.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(1.0F, -7.0F, 0.0F));

        PartDefinition left_plate = left.addOrReplaceChild("left_plate", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_bottom_center_r1 = left_plate.addOrReplaceChild("left_bottom_center_r1", CubeListBuilder.create().texOffs(30, 7).addBox(-3.0F, 1.0F, 4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 41).addBox(-3.0F, -8.0F, 4.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition left_top_left_plate_r1 = left_plate.addOrReplaceChild("left_top_left_plate_r1", CubeListBuilder.create().texOffs(32, 37).addBox(-6.0F, -17.0F, 5.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 46).addBox(-4.0F, -7.0F, 5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 31).addBox(1.0F, -17.0F, 5.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(44, 19).addBox(1.0F, -7.0F, 5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 7.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition left_casing = left.addOrReplaceChild("left_casing", CubeListBuilder.create(), PartPose.offset(-1.0F, 7.0F, 0.0F));

        PartDefinition front_bottom_middle_casing_r1 = left_casing.addOrReplaceChild("front_bottom_middle_casing_r1", CubeListBuilder.create().texOffs(22, 21).addBox(-6.0F, -4.0F, 5.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 39).addBox(-7.0F, -6.0F, 6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 27).addBox(4.0F, -6.0F, 6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition right = body.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(1.0F, -7.0F, 0.0F));

        PartDefinition rightplate = right.addOrReplaceChild("rightplate", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition right_bottom_center_plate_r1 = rightplate.addOrReplaceChild("right_bottom_center_plate_r1", CubeListBuilder.create().texOffs(17, 43).addBox(-3.0F, 1.0F, -7.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 27).addBox(-3.0F, -8.0F, -7.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition right_bottom_left_plate_r1 = rightplate.addOrReplaceChild("right_bottom_left_plate_r1", CubeListBuilder.create().texOffs(24, 46).addBox(1.0F, -7.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 47).addBox(-4.0F, -7.0F, -6.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 32).addBox(1.0F, -17.0F, -6.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-6.0F, -17.0F, -6.0F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 7.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition rightcasing = right.addOrReplaceChild("rightcasing", CubeListBuilder.create(), PartPose.offset(-1.0F, 7.0F, 0.0F));

        PartDefinition front_bottom_middle_casing_r2 = rightcasing.addOrReplaceChild("front_bottom_middle_casing_r2", CubeListBuilder.create().texOffs(0, 23).addBox(-6.0F, -4.0F, -7.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(20, 46).addBox(4.0F, -6.0F, -7.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 23).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition tentacles = core.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition front_left_tentacle = tentacles.addOrReplaceChild("front_left_tentacle", CubeListBuilder.create().texOffs(24, 32).addBox(2.0F, -4.0F, -2.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition front_right_tentacle = tentacles.addOrReplaceChild("front_right_tentacle", CubeListBuilder.create().texOffs(20, 32).addBox(-3.0F, -4.0F, -2.0F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition back_right_tentacle = tentacles.addOrReplaceChild("back_right_tentacle", CubeListBuilder.create().texOffs(45, 45).addBox(-3.0F, -21.0F, 3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

        PartDefinition back_left_tentacle = tentacles.addOrReplaceChild("back_left_tentacle", CubeListBuilder.create().texOffs(28, 27).addBox(2.0F, -21.0F, 3.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animateWalk(ModAnimationDefinitions.SENTINEL_WALK,limbSwing,limbSwingAmount,2f,2.5f);
        this.animate(((SentinelEntity) entity).idleAnimationState,ModAnimationDefinitions.SENTINEL_IDLE,ageInTicks,1f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        sentinel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return sentinel;
    }

}