package net.betterpvp.clans.farming;

import org.bukkit.Material;

public class FarmBlocks {

    public static boolean isCultivation(Material b) {
        return b == Material.PUMPKIN_SEEDS || b == Material.MELON_SEEDS
                || b == Material.WHEAT_SEEDS
                || b == Material.SUGAR_CANE
                || b == Material.POTATO
                || b == Material.POTATOES
                || b == Material.CARROT
                || b == Material.CARROTS
                || b == Material.CACTUS
                || b == Material.MELON_STEM
                || b == Material.WHEAT
                || b == Material.PUMPKIN_STEM
                || b == Material.PUMPKIN
                || b == Material.MELON
                || b == Material.NETHER_WART_BLOCK
                || b == Material.NETHER_WART
                || b == Material.BEETROOT
                || b == Material.BEETROOTS
                || b == Material.SWEET_BERRY_BUSH
                || b == Material.SWEET_BERRIES;
    }


    public static boolean isSeed(Material b) {
        return b == Material.PUMPKIN_SEEDS || b == Material.MELON_SEEDS
                || b == Material.WHEAT_SEEDS || b == Material.SUGAR_CANE
                || b == Material.POTATO
                || b == Material.WHEAT
                || b == Material.CARROT
                || b == Material.CACTUS
                || b == Material.NETHER_WART_BLOCK
                || b == Material.NETHER_WART
                || b == Material.BEETROOT_SEEDS
                || b == Material.SWEET_BERRIES
                || b == Material.SWEET_BERRY_BUSH;
    }
}
