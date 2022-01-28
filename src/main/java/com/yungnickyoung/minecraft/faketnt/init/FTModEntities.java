package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import com.yungnickyoung.minecraft.faketnt.entity.PrimedFakeTnt;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class FTModEntities {
    public static final EntityType<PrimedFakeTnt> FAKE_TNT = FabricEntityTypeBuilder
            .<PrimedFakeTnt>create(MobCategory.MISC, PrimedFakeTnt::new)
            .fireImmune()
            .dimensions(EntityDimensions.fixed(0.98f, 0.98f))
            .trackRangeBlocks(10)
            .trackedUpdateRate(10)
            .build();

    public static void init() {
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(FakeTnt.MOD_ID, "fake_tnt"), FAKE_TNT);
    }
}
