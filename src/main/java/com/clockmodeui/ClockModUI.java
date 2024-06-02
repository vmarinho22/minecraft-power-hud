package com.clockmodeui;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClockModUI implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("clock-mod-ui");

	@Override
	public void onInitialize() {
		LOGGER.info("Init Clock and Compass HUD Mod UI");
	}
}