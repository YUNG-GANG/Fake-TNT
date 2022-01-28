package com.yungnickyoung.minecraft.faketnt;

import com.yungnickyoung.minecraft.faketnt.client.FakeTntClient;
import com.yungnickyoung.minecraft.faketnt.init.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FakeTnt.MOD_ID)
public class FakeTnt {
	public static final String MOD_ID = "faketnt";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public FakeTnt() {
		FTModBlocks.init();
		FTModItems.init();
		FTModEntities.init();
		FTModParticles.init();
		FTModSounds.init();
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> FakeTntClient::init);
	}
}
