package net.baggyboi.endlessendmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.baggyboi.endlessendmod.EndlessEndMod;
import net.baggyboi.endlessendmod.entity.custom.SentinelEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SentinelRenderer extends MobRenderer<SentinelEntity, SentinelModel<SentinelEntity>> {
    public SentinelRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SentinelModel<>(pContext.bakeLayer(ModModelLayers.SENTINEL_LAYER)), .6f);
    }

    @Override
    public ResourceLocation getTextureLocation(SentinelEntity pEntity) {
        return new ResourceLocation(EndlessEndMod.MOD_ID, "textures/entity/sentinel.png");
    }

    @Override
    public void render(SentinelEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}