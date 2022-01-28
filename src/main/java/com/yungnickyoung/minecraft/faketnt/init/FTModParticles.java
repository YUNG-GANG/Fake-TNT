package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;

public class FTModParticles {
    // The state of our particle does not depend on any server logic, so we can just use a simple particle
    public static final SimpleParticleType BLUE_CONFETTI = FabricParticleTypes.simple();
    public static final SimpleParticleType GREEN_CONFETTI = FabricParticleTypes.simple();
    public static final SimpleParticleType PINK_CONFETTI = FabricParticleTypes.simple();
    public static final SimpleParticleType PURPLE_CONFETTI = FabricParticleTypes.simple();
    public static final SimpleParticleType YELLOW_CONFETTI = FabricParticleTypes.simple();

    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(FakeTnt.MOD_ID, "blue_confetti"), BLUE_CONFETTI);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(FakeTnt.MOD_ID, "green_confetti"), GREEN_CONFETTI);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(FakeTnt.MOD_ID, "pink_confetti"), PINK_CONFETTI);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(FakeTnt.MOD_ID, "purple_confetti"), PURPLE_CONFETTI);
        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(FakeTnt.MOD_ID, "yellow_confetti"), YELLOW_CONFETTI);
    }
}
