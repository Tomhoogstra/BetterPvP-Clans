package net.betterpvp.clans.weapon.weapons.legendaries;

import net.betterpvp.clans.Clans;
import net.betterpvp.clans.clans.ClanUtilities;
import net.betterpvp.clans.combat.LogManager;
import net.betterpvp.clans.combat.throwables.ThrowableManager;
import net.betterpvp.clans.combat.throwables.events.ThrowableCollideEntityEvent;
import net.betterpvp.clans.weapon.ILegendary;
import net.betterpvp.clans.weapon.Weapon;
import net.betterpvp.clans.weapon.WeaponManager;
import net.betterpvp.core.framework.UpdateEvent;
import net.betterpvp.core.utility.UtilBlock;
import net.betterpvp.core.utility.restoration.BlockRestoreData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class FireWalkers extends Weapon implements ILegendary {

    public FireWalkers(Clans i) {
        super(i, Material.DIAMOND_BOOTS, (byte) 0, ChatColor.RED + "Flamebringer Treads",
                new String[]{"",
                        ChatColor.GRAY + "Passive: " + ChatColor.YELLOW + "Fire Walking",
                        "",
                        ChatColor.GRAY + "Legend has it these mighty pair of boots were",
                        ChatColor.GRAY + "forged from the volcano of Erebisa thousands of years ago",
                        "",
                        ChatColor.GRAY + "Wherever you may walk, fire shall shadow you.",
                        ""
                }, true, 5.5);

    }

    @EventHandler
    public void fire(UpdateEvent e) {
        if (e.getType() == UpdateEvent.UpdateType.TICK_2) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getEquipment().getBoots() != null) {
                    Weapon weapon = WeaponManager.getWeapon(p.getEquipment().getBoots());
                    if (weapon != null) {
                        if (weapon.equals(this)) {
                            Item fire = p.getWorld().dropItem(p.getLocation().add(0.0D, 0.5D, 0.0D), new ItemStack(Material.BLAZE_POWDER));
                            ThrowableManager.addThrowable(fire, p, getName(), 2000L);
                            fire.setVelocity(new Vector((Math.random() - 0.5D) / 3.0D, Math.random() / 3.0D, (Math.random() - 0.5D) / 3.0D));
                        }
                    }

                }
            }
        }

    }

    @EventHandler
    public void updateEvent(PlayerItemDamageEvent e) {

        if (e.getItem().getType() == Material.DIAMOND_BOOTS) {
            Weapon weapon = WeaponManager.getWeapon(e.getPlayer().getEquipment().getBoots());
            if (weapon != null) {
                if (weapon.equals(this)) {
                    e.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
            if (e.getPlayer().getEquipment().getBoots() != null) {
                Weapon weapon = WeaponManager.getWeapon(e.getPlayer().getEquipment().getBoots());
                if (weapon != null) {
                    if (weapon.equals(this)) {
                        for (Block b : UtilBlock.getInRadius(e.getPlayer().getLocation(), 2, 2).keySet()) {

                            if (b.getType() == Material.LAVA) {
                                if (e.getPlayer().getLocation().getBlock().isLiquid()) continue;
                                if (b.getRelative(BlockFace.UP).getType() != Material.AIR) continue;

                                new BlockRestoreData(b, b.getType(), (byte) 0, 5000L);
                                b.setType(Material.MAGMA_BLOCK);
                            }
                        }
                    }
                }
            }
        }
    }




    @EventHandler
    public void onCollide(ThrowableCollideEntityEvent e) {
        if (e.getThrowable().getSkillName().equals(getName())) {
            if (e.getThrowable().getThrower() instanceof Player) {
                Player damager = (Player) e.getThrowable().getThrower();
                if (damager != null) {
                    if (e.getCollision() instanceof Player) {
                        if (!ClanUtilities.canHurt(damager, (Player) e.getCollision())) {
                            return; //TODO cancel this event
                        }
                    }

                    if (e.getCollision().getFireTicks() > 0) return;
                    LogManager.addLog(e.getCollision(), damager, "Flamebringer Treads", 1);
                    e.getCollision().setFireTicks(80);
                }
            }
        }
    }

    @Override
    public boolean isTextured() {
        return false;
    }
}
