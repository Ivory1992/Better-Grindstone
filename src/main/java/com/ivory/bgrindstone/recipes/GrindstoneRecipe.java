package com.ivory.bgrindstone.recipes;

import com.ivory.bgrindstone.BGrindstone;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.GrindstoneEvent;

@EventBusSubscriber(modid = BGrindstone.MODID)
public class GrindstoneRecipe {

    @SubscribeEvent
    public static void onPlace(GrindstoneEvent.OnPlaceItem event) {
        ItemStack top = event.getTopItem();
        ItemStack bottom = event.getBottomItem();
        ItemStack newtop = top.copy();

        if (top.isEnchanted() && bottom.is(Items.BOOK) && bottom.getCount() == 1){
            newtop.set(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
            event.setOutput(newtop);
            event.setXp(0);
        }
    }

    @SubscribeEvent
    public static void onTake(GrindstoneEvent.OnTakeItem event) {
        ItemStack top = event.getTopItem();
        ItemStack bottom = event.getBottomItem();

        if (top.isEnchanted() && bottom.is(Items.BOOK) && bottom.getCount() == 1){
            ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);
            book.set(DataComponents.STORED_ENCHANTMENTS, top.getEnchantments());
            event.setNewBottomItem(book);
        }
    }
}
