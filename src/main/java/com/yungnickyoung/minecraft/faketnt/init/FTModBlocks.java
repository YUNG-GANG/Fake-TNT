package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import com.yungnickyoung.minecraft.faketnt.block.FakeTntBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class FTModBlocks {
    public static final Block FAKE_TNT = new FakeTntBlock(BlockBehaviour.Properties
            .of(Material.EXPLOSIVE)
            .sound(SoundType.GRASS)
            .instabreak());

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, FTModBlocks::registerBlocks);
    }

    private static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(FAKE_TNT.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "fake_tnt")));
    }
}
