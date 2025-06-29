package net.hsneptune.neconomy;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

import net.minecraft.block.Blocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.hsneptune.neconomy.events.ChangeEventListener;

public class NeptuneEconomy implements ModInitializer {
	public static final String MOD_ID = "neconomy";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Starting Neptune Economy!");
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			if (state.getBlock() == Blocks.GOLD_ORE && !player.getWorld().isClient()) {
				ChangeEventListener.goldCount++;
			} else if (state.getBlock() == Blocks.DIAMOND_ORE && !player.getWorld().isClient()){
				ChangeEventListener.diamondCount++;
			} else if (state.getBlock() == Blocks.EMERALD_ORE && !player.getWorld().isClient()){
				ChangeEventListener.emeraldCount++;
			} else if (state.getBlock() == Blocks.IRON_ORE && !player.getWorld().isClient()){
				ChangeEventListener.ironCount++;
			}
		});

	}
}