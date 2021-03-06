package net.betterpvp.clans.classes.roles;

import net.betterpvp.clans.classes.Role;
import org.bukkit.Material;

public class Ranger extends Role {

    public Ranger() {
        super("Ranger");

    }

    @Override
    public Material getHelmet() {
        return Material.CHAINMAIL_HELMET;
    }

    @Override
    public Material getChestplate() {
        return Material.CHAINMAIL_CHESTPLATE;
    }

    @Override
    public Material getLeggings() {
        return Material.CHAINMAIL_LEGGINGS;
    }

    @Override
    public Material getBoots() {
        return Material.CHAINMAIL_BOOTS;
    }


}
