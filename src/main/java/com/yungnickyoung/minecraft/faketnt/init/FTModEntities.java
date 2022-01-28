package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import com.yungnickyoung.minecraft.faketnt.entity.PrimedFakeTnt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class FTModEntities {
    public static EntityType<PrimedFakeTnt> FAKE_TNT = EntityType.Builder
            .<PrimedFakeTnt>of(PrimedFakeTnt::new, MobCategory.MISC)
            .fireImmune()
            .sized(0.98F, 0.98F)
            .clientTrackingRange(10)
            .updateInterval(10)
            .build("fake_tnt");

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(EntityType.class, FTModEntities::registerEntities);
    }

    private static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().register(FAKE_TNT.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "fake_tnt")));
    }
}
