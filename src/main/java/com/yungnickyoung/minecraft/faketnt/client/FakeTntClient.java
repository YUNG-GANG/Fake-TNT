package com.yungnickyoung.minecraft.faketnt.client;

import com.yungnickyoung.minecraft.faketnt.init.FTModEntities;
import com.yungnickyoung.minecraft.faketnt.init.FTModParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class FakeTntClient {
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(FakeTntClient::registerEntityRenderers);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(FakeTntClient::registerParticles);
    }

    private static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers.RegisterRenderers event) {
        event.registerEntityRenderer(FTModEntities.FAKE_TNT, FakeTntRenderer::new);
    }

    private static void registerParticles(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(FTModParticles.BLUE_CONFETTI, ConfettiParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(FTModParticles.GREEN_CONFETTI, ConfettiParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(FTModParticles.PINK_CONFETTI, ConfettiParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(FTModParticles.PURPLE_CONFETTI, ConfettiParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(FTModParticles.YELLOW_CONFETTI, ConfettiParticle.Provider::new);
    }
}
