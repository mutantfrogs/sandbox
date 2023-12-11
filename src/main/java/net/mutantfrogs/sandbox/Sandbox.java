package net.mutantfrogs.sandbox;

import net.fabricmc.api.ModInitializer;

import net.mutantfrogs.sandbox.item.ModItemGroups;
import net.mutantfrogs.sandbox.item.ModItems;
import net.mutantfrogs.sandbox.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sandbox implements ModInitializer {
	public static final String MOD_ID = "sandbox";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModSounds.registerSounds();



	}
}