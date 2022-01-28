package com.yungnickyoung.minecraft.faketnt.init;

import com.yungnickyoung.minecraft.faketnt.FakeTnt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class FTModItems {
    public static Item FAKE_TNT = new BlockItem(FTModBlocks.FAKE_TNT, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, FTModItems::registerItems);
    }

    private static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(FAKE_TNT.setRegistryName(new ResourceLocation(FakeTnt.MOD_ID, "fake_tnt")));
    }
}
