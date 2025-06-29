package net.hsneptune.neconomy.mixin;

import net.hsneptune.neconomy.events.ChangeHandler;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.server.world.ServerWorld;
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


        // Despawn detection
        if (itemEntity.age >= 5999) {
            ChangeHandler.reduceCounts(item, (ServerWorld) world);
        }

        // Lava destruction detection
        if (itemEntity.isOnFire()) {
            ChangeHandler.reduceCounts(item, (ServerWorld) world);
        }
    }

}
