package net.hsneptune.neconomy;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.hsneptune.neconomy.events.ChangeHandler;
import net.hsneptune.neconomy.world.OreCounter;
import net.minecraft.item.BlockItem; // Import BlockItem
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NeptuneEconomy implements ModInitializer {
	public static final String MOD_ID = "neconomy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier ORE_COUNTER_DATA_ID = Identifier.of(MOD_ID, "ore_counter");

	@Override
	public void onInitialize() {
		LOGGER.info("Starting Neptune Economy!");

		ServerWorldEvents.LOAD.register((MinecraftServer server, ServerWorld world) -> {
			if (world.getRegistryKey() == World.OVERWORLD) {
				OreCounter oreCounter = getOreCounter(world);
				oreCounter.init();

				LOGGER.info("Loaded Ore Counts for Overworld (via instance):");
				oreCounter.getCounts();
			}
		});

		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
			if (!world.isClient()) {
				ChangeHandler.blockBrokenChange(state, player, (ServerWorld) world);
			}
		});

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			// Add logging here to see if the UseBlockCallback is triggered and on which side
			LOGGER.info("UseBlockCallback triggered. Client: {}, Hand: {}", world.isClient(), hand);

			// Ensure we are on the server side and it's a main or off hand interaction
			if (!world.isClient() && (hand == Hand.MAIN_HAND || hand == Hand.OFF_HAND)) {
				// Check if the item in hand is a BlockItem (suggests an attempt to place a block)
				if (player.getStackInHand(hand).getItem() instanceof BlockItem) {
					LOGGER.info("Player attempting to place a block with item: {}", player.getStackInHand(hand).getItem().getName().getString());
					ChangeHandler.reduceCounts(player.getStackInHand(hand).getItem(), (ServerWorld) world);
				} else {
					LOGGER.info("Player used non-block item: {}", player.getStackInHand(hand).getItem().getName().getString());
				}
			}
			return ActionResult.PASS;
		});
	}

	public static OreCounter getOreCounter(ServerWorld world) {
		PersistentStateManager persistentStateManager = world.getPersistentStateManager();
		return persistentStateManager.getOrCreate(OreCounter.TYPE, MOD_ID + "_" + ORE_COUNTER_DATA_ID.getPath());
	}

	public static void setGoldCount(ServerWorld world, int count) {
		LOGGER.info("Setting Gold Count for world {}: Old: {}, New: {}", world.getRegistryKey().getValue(), getGoldCount(world), count);
		getOreCounter(world).setGoldCount(count);
	}
	public static void setDiamondCount(ServerWorld world, int count) {
		LOGGER.info("Setting Diamond Count for world {}: Old: {}, New: {}", world.getRegistryKey().getValue(), getDiamondCount(world), count);
		getOreCounter(world).setDiamondCount(count);
	}
	public static void setIronCount(ServerWorld world, int count) {
		LOGGER.info("Setting Iron Count for world {}: Old: {}, New: {}, ", world.getRegistryKey().getValue(), getIronCount(world), count);
		getOreCounter(world).setIronCount(count);
	}
	public static void setEmeraldCount(ServerWorld world, int count) {
		LOGGER.info("Setting Emerald Count for world {}: Old: {}, New: {}", world.getRegistryKey().getValue(), getEmeraldCount(world), count);
		getOreCounter(world).setEmeraldCount(count);
	}
	public static void setDebrisCount(ServerWorld world, int count) {
		LOGGER.info("Setting Debris Count for world {}: Old: {}, New: {}", world.getRegistryKey().getValue(), getDebrisCount(world), count);
		getOreCounter(world).setDebrisCount(count);
	}

	public static void addGold(ServerWorld world, int delta) {
		int oldVal = getGoldCount(world);
		getOreCounter(world).addGold(delta);
		LOGGER.info("Added {} to Gold Count for world {}: Old: {}, New: {}", delta, world.getRegistryKey().getValue(), oldVal, getGoldCount(world));
	}
	public static void addIron(ServerWorld world, int delta) {
		int oldVal = getIronCount(world);
		getOreCounter(world).addIron(delta);
		LOGGER.info("Added {} to Iron Count for world {}: Old: {}, New: {}", delta, world.getRegistryKey().getValue(), oldVal, getIronCount(world));
	}
	public static void addDiamond(ServerWorld world, int delta) {
		int oldVal = getDiamondCount(world);
		getOreCounter(world).addDiaz(delta);
		LOGGER.info("Added {} to Diamond Count for world {}: Old: {}, New: {}", delta, world.getRegistryKey().getValue(), oldVal, getDiamondCount(world));
	}
	public static void addEmerald(ServerWorld world, int delta) {
		int oldVal = getEmeraldCount(world);
		getOreCounter(world).addEmerald(delta);
		LOGGER.info("Added {} to Emerald Count for world {}: Old: {}, New: {}", delta, world.getRegistryKey().getValue(), oldVal, getEmeraldCount(world));
	}
	public static void addDebris(ServerWorld world, int delta) {
		int oldVal = getDebrisCount(world);
		getOreCounter(world).addDebris(delta);
		LOGGER.info("Added {} to Debris Count for world {}: Old: {}, New: {}", delta, world.getRegistryKey().getValue(), oldVal, getDebrisCount(world));
	}

	public static int getGoldCount(ServerWorld world) {
		return getOreCounter(world).getGoldCount();
	}
	public static int getDiamondCount(ServerWorld world) {
		return getOreCounter(world).getDiamondCount();
	}
	public static int getIronCount(ServerWorld world) {
		return getOreCounter(world).getIronCount();
	}
	public static int getEmeraldCount(ServerWorld world) {
		return getOreCounter(world).getEmeraldCount();
	}
	public static int getDebrisCount(ServerWorld world) {
		return getOreCounter(world).getDebrisCount();
	}

	public static void getCounts(ServerWorld world){
		getOreCounter(world).getCounts();
	}

	public static void initializeOreCountersForWorld(ServerWorld world) {
		getOreCounter(world).init();
	}
}