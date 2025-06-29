package net.hsneptune.neconomy;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import net.hsneptune.neconomy.events.ChangeHandler;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.hsneptune.neconomy.world.OreCounter;

public class NeptuneEconomy implements ModInitializer {
	public static final String MOD_ID = "neconomy";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Starting Neptune Economy!");
		OreCounter.init();
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			ChangeHandler.blockBrokenChange(state, player);
		});
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (!world.isClient && hand == Hand.MAIN_HAND || hand == Hand.OFF_HAND) {
				ChangeHandler.reduceCounts(player.getStackInHand(hand).getItem());
			}
            return ActionResult.PASS;
		});

	}
}