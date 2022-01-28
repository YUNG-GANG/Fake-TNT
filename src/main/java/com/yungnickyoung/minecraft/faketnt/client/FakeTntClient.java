package com.yungnickyoung.minecraft.faketnt.client;

import com.yungnickyoung.minecraft.faketnt.init.FTModEntities;
import com.yungnickyoung.minecraft.faketnt.init.FTModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class FakeTntClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(FTModEntities.FAKE_TNT, FakeTntRenderer::new);
        ParticleFactoryRegistry.getInstance().register(FTModParticles.BLUE_CONFETTI, ConfettiParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(FTModParticles.GREEN_CONFETTI, ConfettiParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(FTModParticles.PINK_CONFETTI, ConfettiParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(FTModParticles.PURPLE_CONFETTI, ConfettiParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(FTModParticles.YELLOW_CONFETTI, ConfettiParticle.Provider::new);
    }
}
