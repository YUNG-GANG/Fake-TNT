package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import com.yungnickyoung.minecraft.faketnt.block.FakeTntBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class FTModBlocks {
    public static final Block FAKE_TNT = new FakeTntBlock(FabricBlockSettings
            .of(Material.EXPLOSIVE)
            .sound(SoundType.GRASS)
            .instabreak());

    public static void init() {
        Registry.register(Registry.BLOCK, new ResourceLocation(FakeTnt.MOD_ID, "fake_tnt"), FAKE_TNT);
        Registry.register(Registry.ITEM,
                new ResourceLocation(FakeTnt.MOD_ID, "fake_tnt"),
                new BlockItem(FAKE_TNT, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
    }
}
