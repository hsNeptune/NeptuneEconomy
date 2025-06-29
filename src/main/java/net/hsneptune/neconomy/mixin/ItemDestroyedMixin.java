package net.hsneptune.neconomy.mixin;

import net.hsneptune.neconomy.events.ChangeEventListener;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.world.World;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemDestroyedMixin{
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onItemTick(CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity)(Object)this;
        World world = itemEntity.getWorld();

        if (world.isClient) return;

        ItemStack stack = itemEntity.getStack();
        Item item = stack.getItem();

        // Only act on specific items
        if (item != Items.DIAMOND && item != Items.DIAMOND_BLOCK && item != Items.RAW_IRON && item != Items.RAW_IRON_BLOCK && item != Items.RAW_GOLD && item != Items.RAW_GOLD_BLOCK && item != Items.EMERALD && item != Items.EMERALD_BLOCK) return;

        // Despawn detection
        if (itemEntity.age >= 5999) {
            if (item == Items.DIAMOND)
                ChangeEventListener.diamondCount--;
            else if (item == Items.DIAMOND_BLOCK)
                ChangeEventListener.diamondCount -= 9;

            else if (item == Items.EMERALD)
                ChangeEventListener.emeraldCount--;
            else if (item == Items.EMERALD_BLOCK)
                ChangeEventListener.emeraldCount -= 9;

            else if (item == Items.RAW_IRON)
                ChangeEventListener.ironCount--;
            else if (item == Items.RAW_IRON_BLOCK)
                ChangeEventListener.ironCount -= 9;

            else if (item == Items.RAW_GOLD)
                ChangeEventListener.goldCount--;
            else if (item == Items.RAW_GOLD_BLOCK)
                ChangeEventListener.ironCount -= 9;
        }

        // Lava destruction detection
        if (itemEntity.isOnFire()) {
            if (item == Items.DIAMOND)
                ChangeEventListener.diamondCount--;
            else if (item == Items.DIAMOND_BLOCK)
                ChangeEventListener.diamondCount -= 9;

            else if (item == Items.EMERALD)
                ChangeEventListener.emeraldCount--;
            else if (item == Items.EMERALD_BLOCK)
                ChangeEventListener.emeraldCount -= 9;

            else if (item == Items.RAW_IRON)
                ChangeEventListener.ironCount--;
            else if (item == Items.RAW_IRON_BLOCK)
                ChangeEventListener.ironCount -= 9;

            else if (item == Items.RAW_GOLD)
                ChangeEventListener.goldCount--;
            else if (item == Items.RAW_GOLD_BLOCK)
                ChangeEventListener.ironCount -= 9;
        }
    }
}
