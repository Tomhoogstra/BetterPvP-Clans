package net.betterpvp.clans.skills.selector.skills.knight;

import net.betterpvp.clans.Clans;
import net.betterpvp.clans.classes.events.CustomDamageEvent;
import net.betterpvp.clans.gamer.GamerManager;
import net.betterpvp.clans.skills.Types;
import net.betterpvp.clans.skills.selector.skills.Skill;
import net.betterpvp.core.framework.UpdateEvent;
import net.betterpvp.core.framework.UpdateEvent.UpdateType;
import net.betterpvp.core.utility.UtilMessage;
import net.betterpvp.core.utility.UtilTime;
import net.betterpvp.core.utility.recharge.RechargeManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import java.util.Arrays;
import java.util.WeakHashMap;

public class Swordsmanship extends Skill {

    public Swordsmanship(Clans i) {
        super(i, "Swordsmanship", "Knight", getSwords, noActions, 3, false, false);

    }

    private WeakHashMap<Player, Integer> charges = new WeakHashMap<>();

    @Override
    public String[] getDescription(int level) {

        return new String[]{
                "Prepare a powerful sword attack,",
                "You gain 1 charge every 3 seconds.",
                "You can store a maximum of " + ChatColor.GREEN + (level) + ChatColor.GRAY + " charges",
                "",
                "When you attack, your damage is",
                "increased by the number of your charges",
                "and your charges are reset to 0.",
                "",
                "This only applies to swords."
        };
    }

    @Override
    public Types getType() {

        return Types.PASSIVE_B;
    }

    @Override
    public double getRecharge(int level) {

        return 0;
    }

    @Override
    public float getEnergy(int level) {

        return 0;
    }


    @Override
    public boolean usageCheck(Player player) {

        return false;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(CustomDamageEvent e) {
        if (e.getCause() != DamageCause.ENTITY_ATTACK) return;
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();

            if (hasSkill(p, this)) {
                if (charges.containsKey(p)) {

                    if (Arrays.asList(getMaterials()).contains(p.getInventory().getItemInMainHand().getType())) {
                        e.setDamage(e.getDamage() + (charges.get(p) * 0.5));
                        charges.remove(p);
                    }
                }
            }

        }
    }

    @EventHandler
    public void addCharge(UpdateEvent event) {
        if (event.getType() == UpdateType.FAST) {

            for (Player cur : Bukkit.getOnlinePlayers()) {
                if (hasSkill(cur, this)) {
                    if (charges.containsKey(cur)) {
                        if (UtilTime.elapsed(GamerManager.getOnlineGamer(cur).getLastDamaged(), 2500)) {
                            if (RechargeManager.getInstance().add(cur, getName(), 3, false)) {
                                if (charges.get(cur) < getLevel(cur)) {
                                    int charge = 1;

                                    charge += charges.get(cur);

                                    charge = Math.min(getLevel(cur), charge);
                                    UtilMessage.message(cur, getClassType(), "Swordsmanship charge: " + ChatColor.YELLOW + charge);
                                    charges.put(cur, charge);
                                }
                            }
                        }
                    } else {
                        charges.put(cur, 0);
                    }
                }
            }

        }
    }


}
