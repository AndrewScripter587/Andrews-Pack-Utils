package com.andrewgaming;


import net.fabricmc.api.ModInitializer;

import static net.fabricmc.loader.impl.FabricLoaderImpl.MOD_ID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AndrewsPackUtilities implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		SetupCommands.Init(); // Initialize Commands
		LOGGER.info("Loaded Andrew's Pack Utilities");
	}
}

