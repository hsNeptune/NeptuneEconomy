package net.hsneptune.neconomy.events;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
public class ChangeEventListener implements ModInitializer {

    public static int goldCount;
    public static int diamondCount;
    public static int ironCount;
    public static int emeraldCount;

    @Override
    public void onInitialize() {

    }


}
