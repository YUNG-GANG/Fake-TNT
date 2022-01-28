package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class FTModSounds {
    public static SoundEvent SURPRISE_SOUND = new SoundEvent(new ResourceLocation(FakeTnt.MOD_ID, "surprise"));

    public static void init() {
        Registry.register(Registry.SOUND_EVENT, new ResourceLocation(FakeTnt.MOD_ID, "surprise"), SURPRISE_SOUND);
    }
}
