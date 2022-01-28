package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class FTModSounds {
    public static SoundEvent SURPRISE_SOUND = new SoundEvent(new ResourceLocation(FakeTnt.MOD_ID, "surprise"));

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(SoundEvent.class, FTModSounds::registerSounds);
    }

    private static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(SURPRISE_SOUND.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "surprise")));
    }
}
