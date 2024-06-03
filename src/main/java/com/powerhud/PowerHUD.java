package com.powerhud;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerHUD implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("power-hud");

	@Override
	public void onInitialize() {
		LOGGER.info("Init Power HUD Mod");
	}
}