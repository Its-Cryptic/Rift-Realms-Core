package dev.cryptic.riftrealmscore.api.util.Calculations;

import dev.cryptic.riftrealmscore.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class Calculations {

    public static boolean isAwesome(Player player, ItemStack item) {
        if (item.getItem().equals(ModItems.MY_AWESOME_SWORD.get())) {
            return true;
        } else {
            return false;
        }
    }

}

