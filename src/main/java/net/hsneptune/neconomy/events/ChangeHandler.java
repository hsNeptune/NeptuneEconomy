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
        if (!player.getWorld().isClient()) {
            return;
        }

        switch (state.getBlock()) {
            case Blocks.GOLD_ORE:
            case Blocks.DEEPSLATE_GOLD_ORE:
                OreCounter.addGold(1);
                break;
            case Blocks.DIAMOND_ORE:
            case Blocks.DEEPSLATE_DIAMOND_ORE:
                OreCounter.addDiaz(1);
                break;
            case Blocks.EMERALD_ORE:
            case Blocks.DEEPSLATE_EMERALD_ORE:
                OreCounter.addEmerald(1);
                break;
            case Blocks.IRON_ORE:
            case Blocks.DEEPSLATE_IRON_ORE:
                OreCounter.addIron(1);
                break;
            case Blocks.RAW_GOLD_BLOCK:
                OreCounter.addGold(9);
                break;
            case Blocks.RAW_IRON_BLOCK:
                OreCounter.addIron(9);
                break;
            case Blocks.ANCIENT_DEBRIS:
                OreCounter.addDebris(9);
                break;
            default:
                break;
        }

        OreCounter.getCounts();
    }


}
