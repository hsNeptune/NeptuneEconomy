package net.hsneptune.neconomy.events;

import net.hsneptune.neconomy.NeptuneEconomy;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ChangeHandler {

    public static void reduceCounts(Item item, ServerWorld world) {
        NeptuneEconomy.LOGGER.info("reduceCounts called with item: {} for world {}", item.getName().getString(), world.getRegistryKey().getValue());

        if (item == Items.DIAMOND)
            NeptuneEconomy.addGold(world, -1);
        else if (item == Items.DIAMOND_BLOCK)
            NeptuneEconomy.addDiamond(world, -9);

        else if (item == Items.EMERALD)
            NeptuneEconomy.addEmerald(world, -1);
        else if (item == Items.EMERALD_BLOCK)
            NeptuneEconomy.addEmerald(world, -9);

        else if (item == Items.RAW_IRON)
            NeptuneEconomy.addIron(world, -1);
        else if (item == Items.RAW_IRON_BLOCK)
            NeptuneEconomy.addIron(world, -9);

        else if (item == Items.RAW_GOLD)
            NeptuneEconomy.addGold(world, -1);
        else if (item == Items.RAW_GOLD_BLOCK)
            NeptuneEconomy.addGold(world, -9);

            // This was previously subtracting from Gold. Corrected to subtract from Debris.
        else if (item == Items.ANCIENT_DEBRIS)
            NeptuneEconomy.addDebris(world, -1); // Corrected to addDebris

        NeptuneEconomy.getCounts(world);
    }

    public static void blockBrokenChange(BlockState state, PlayerEntity player, ServerWorld world) {
        if (world.isClient()) {
            return;
        }

        NeptuneEconomy.LOGGER.info("blockBrokenChange called for block: {} in world {}", state.getBlock().getName().getString(), world.getRegistryKey().getValue());


        if (state.getBlock() == Blocks.GOLD_ORE || state.getBlock() == Blocks.DEEPSLATE_GOLD_ORE) {
            NeptuneEconomy.addGold(world, 1);
        } else if (state.getBlock() == Blocks.DIAMOND_ORE || state.getBlock() == Blocks.DEEPSLATE_DIAMOND_ORE) {
            NeptuneEconomy.addDiamond(world, 1);
        } else if (state.getBlock() == Blocks.EMERALD_ORE || state.getBlock() == Blocks.DEEPSLATE_EMERALD_ORE) {
            NeptuneEconomy.addEmerald(world, 1);
        } else if (state.getBlock() == Blocks.IRON_ORE || state.getBlock() == Blocks.DEEPSLATE_IRON_ORE) {
            NeptuneEconomy.addIron(world, 1);
        } else if (state.getBlock() == Blocks.RAW_GOLD_BLOCK) {
            NeptuneEconomy.addGold(world, 9);
        } else if (state.getBlock() == Blocks.RAW_IRON_BLOCK) {
            NeptuneEconomy.addIron(world, 9);
        } else if (state.getBlock() == Blocks.ANCIENT_DEBRIS) {
            NeptuneEconomy.addDebris(world, 9);
        }

        NeptuneEconomy.getCounts(world);
    }
}