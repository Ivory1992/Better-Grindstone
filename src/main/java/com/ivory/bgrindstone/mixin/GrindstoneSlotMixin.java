package com.ivory.bgrindstone.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.inventory.GrindstoneMenu$3")
public abstract class GrindstoneSlotMixin extends Slot {

    public GrindstoneSlotMixin(Container container, int slot, int x, int y){
        super(container, slot, x, y);
    }

    @Inject(method = "mayPlace", at = @At("HEAD"), cancellable = true)
    private void bgrindstone$allowBook(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(Items.BOOK)) {
            cir.setReturnValue(true);
        }
    }

    @Override
    public int getMaxStackSize(){
        return 1;
    }

    @Override
    public int getMaxStackSize(ItemStack stack){
        return 1;
    }
}
