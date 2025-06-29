package net.hsneptune.neconomy.events;

import net.hsneptune.neconomy.world.OreCounter;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class ChangeHandler {
    public static void reduceCounts(Item item) {
        if (item == Items.DIAMOND)
            OreCounter.addGold(-1);
        else if (item == Items.DIAMOND_BLOCK)
            OreCounter.addDiaz(-9);

        else if (item == Items.EMERALD)
            OreCounter.addEmerald(-1);
        else if (item == Items.EMERALD_BLOCK)
            OreCounter.addEmerald(-9);

        else if (item == Items.RAW_IRON)
            OreCounter.addIron(-1);
        else if (item == Items.RAW_IRON_BLOCK)
            OreCounter.addIron(-9);

        else if (item == Items.RAW_GOLD)
            OreCounter.addGold(-1);
        else if (item == Items.RAW_GOLD_BLOCK)
            OreCounter.addGold(-9);

        else if (item == Items.ANCIENT_DEBRIS)
            OreCounter.addGold(-1);
        OreCounter.getCounts();
    }
    public static void blockBrokenChange(BlockState state, PlayerEntity player) {
        if (state.getBlock() == Blocks.GOLD_ORE || state.getBlock() == Blocks.DEEPSLATE_GOLD_ORE && !player.getWorld().isClient()) {
            OreCounter.addGold(1);
            OreCounter.getCounts();
        }  else if (state.getBlock() == Blocks.DIAMOND_ORE || state.getBlock() == Blocks.DEEPSLATE_DIAMOND_ORE && !player.getWorld().isClient()){
            OreCounter.addDiaz(1);
            OreCounter.getCounts();
        } else if (state.getBlock() == Blocks.EMERALD_ORE || state.getBlock() == Blocks.DEEPSLATE_EMERALD_ORE && !player.getWorld().isClient()){
            OreCounter.addEmerald(1);
            OreCounter.getCounts();
        } else if (state.getBlock() == Blocks.IRON_ORE || state.getBlock() == Blocks.DEEPSLATE_IRON_ORE && !player.getWorld().isClient()){
            OreCounter.addIron(1);
            OreCounter.getCounts();
        } else if (state.getBlock() == Blocks.RAW_GOLD_BLOCK && !player.getWorld().isClient()){
            OreCounter.addGold(9);
            OreCounter.getCounts();
        } else if (state.getBlock() == Blocks.RAW_IRON_BLOCK && !player.getWorld().isClient()){
            OreCounter.addIron(9);
            OreCounter.getCounts();
        } else if (state.getBlock() == Blocks.RAW_IRON_BLOCK && !player.getWorld().isClient()) {
            OreCounter.addIron(9);
            OreCounter.getCounts();
        } else if (state.getBlock() == Blocks.ANCIENT_DEBRIS && !player.getWorld().isClient()){
            OreCounter.addDebris(1);
            OreCounter.getCounts();
        }
    }


}
