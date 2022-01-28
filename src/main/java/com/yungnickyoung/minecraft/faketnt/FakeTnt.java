package com.yungnickyoung.minecraft.faketnt;

import com.yungnickyoung.minecraft.faketnt.init.FTModBlocks;
import com.yungnickyoung.minecraft.faketnt.init.FTModEntities;
import com.yungnickyoung.minecraft.faketnt.init.FTModParticles;
import com.yungnickyoung.minecraft.faketnt.init.FTModSounds;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FakeTnt implements ModInitializer {
	public static final String MOD_ID = "faketnt";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FTModBlocks.init();
		FTModEntities.init();
		FTModParticles.init();
		FTModSounds.init();
	}
}
