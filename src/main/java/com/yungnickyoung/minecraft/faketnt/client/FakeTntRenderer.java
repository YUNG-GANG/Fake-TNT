package com.yungnickyoung.minecraft.faketnt.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.yungnickyoung.minecraft.faketnt.entity.PrimedFakeTnt;
import com.yungnickyoung.minecraft.faketnt.init.FTModBlocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FakeTntRenderer extends EntityRenderer<PrimedFakeTnt> {
    public FakeTntRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.5f;
    }

    @Override
    public void render(PrimedFakeTnt primedTnt, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        poseStack.translate(0.0, 0.5, 0.0);
        int fuseTime = primedTnt.getFuse();
        if ((float)fuseTime - g + 1.0f < 10.0f) {
            float h = 1.0f - ((float)fuseTime - g + 1.0f) / 10.0f;
            h = Mth.clamp(h, 0.0f, 1.0f);
            h *= h;
            h *= h;
            float k = 1.0f + h * 0.3f;
            poseStack.scale(k, k, k);
        }
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0f));
        poseStack.translate(-0.5, -0.5, 0.5);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0f));
        TntMinecartRenderer.renderWhiteSolidBlock(FTModBlocks.FAKE_TNT.defaultBlockState(), poseStack, multiBufferSource, i, fuseTime / 5 % 2 == 0);
        poseStack.popPose();
        super.render(primedTnt, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    public ResourceLocation getTextureLocation(PrimedFakeTnt primedTnt) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
