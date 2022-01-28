package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class FTModParticles {
    // The state of our particle does not depend on any server logic, so we can just use a simple particle
    public static final SimpleParticleType BLUE_CONFETTI = new SimpleParticleType(false);
    public static final SimpleParticleType GREEN_CONFETTI = new SimpleParticleType(false);
    public static final SimpleParticleType PINK_CONFETTI = new SimpleParticleType(false);
    public static final SimpleParticleType PURPLE_CONFETTI = new SimpleParticleType(false);
    public static final SimpleParticleType YELLOW_CONFETTI = new SimpleParticleType(false);

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(ParticleType.class, FTModParticles::registerParticles);
    }

    private static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        event.getRegistry().register(BLUE_CONFETTI.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "blue_confetti")));
        event.getRegistry().register(GREEN_CONFETTI.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "green_confetti")));
        event.getRegistry().register(PINK_CONFETTI.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "pink_confetti")));
        event.getRegistry().register(PURPLE_CONFETTI.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "purple_confetti")));
        event.getRegistry().register(YELLOW_CONFETTI.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "yellow_confetti")));
    }
}
