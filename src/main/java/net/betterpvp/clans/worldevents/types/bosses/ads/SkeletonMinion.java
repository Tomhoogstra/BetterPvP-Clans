package net.betterpvp.clans.worldevents.types.bosses.ads;

import net.betterpvp.clans.worldevents.types.WorldEventMinion;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkeletonMinion extends WorldEventMinion {


    public SkeletonMinion(WitherSkeleton ent) {
        super(ent);


        ent.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
        ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(getMaxHealth());
        ent.setHealth(getMaxHealth());
        ent.setCustomName(getDisplayName());
        ent.setCustomNameVisible(true);
        ent.setRemoveWhenFarAway(false);
        ent.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));

    }

    @Override
    public String getDisplayName() {

        return ChatColor.BLUE + "Skeleton Minion";
    }

    @Override
    public double getMaxHealth() {

        return 200;
    }

}
