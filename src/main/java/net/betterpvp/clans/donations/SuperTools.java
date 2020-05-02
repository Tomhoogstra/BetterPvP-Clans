package net.betterpvp.clans.donations;

import net.betterpvp.clans.Clans;
import net.betterpvp.clans.clans.Clan;
import net.betterpvp.clans.clans.ClanUtilities;
import net.betterpvp.clans.classes.Energy;
import net.betterpvp.core.client.Client;
import net.betterpvp.core.client.ClientUtilities;
import net.betterpvp.core.donation.IPerk;
import net.betterpvp.core.donation.Perk;
import net.betterpvp.core.framework.BPVPListener;
import net.betterpvp.core.punish.PunishManager;
import net.betterpvp.core.utility.UtilFormat;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SuperTools extends BPVPListener<Clans> implements IPerk {

    public SuperTools(Clans instance) {
        super(instance);
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Client client = ClientUtilities.getOnlineClient(e.getPlayer());
            if (client != null) {
                if (!client.hasDonation(getPerk().getName())) return;
                if (PunishManager.isBuildLocked(e.getPlayer().getUniqueId())) return;

                Block block = e.getClickedBlock();
                Clan blockClan = ClanUtilities.getClan(block.getChunk());
                if (blockClan != null) {
                    if (blockClan != ClanUtilities.getClan(e.getPlayer())) {
                        return;
                    }
                }

                Material mat = block.getType();
                ItemStack hand = e.getPlayer().getEquipment().getItemInMainHand();
                if (hand == null) return;
                if (hand.getType() == Material.DIAMOND_AXE) {
                    if (mat.name().contains("WOOD") || mat.name().contains("_LOG") || mat == Material.MELON || mat == Material.PUMPKIN) {
                        superTool(e.getPlayer(), block);
                    }
                } else if (hand.getType() == Material.DIAMOND_PICKAXE) {
                    if ((mat.name().contains("STONE") && mat != Material.GLOWSTONE) || mat.name().contains("_ORE") || mat.name().contains("BRICK")
                            || mat.name().contains("COAL")) {
                        superTool(e.getPlayer(), block);
                    }
                } else if (hand.getType() == Material.DIAMOND_SHOVEL) {
                    if (mat.name().contains("GRASS") || mat.name().contains("DIRT") || mat == Material.SAND || mat == Material.GRAVEL
                            || mat == Material.SOUL_SAND || mat == Material.FARMLAND){
                        superTool(e.getPlayer(), block);
                    }
                }

            }
        }
    }

    private void superTool(Player player, Block block) {
        ItemStack hand = player.getEquipment().getItemInMainHand();
        if (Energy.use(player, UtilFormat.cleanString(getPerk().getName()), 3, true)) {
            short dura = (short) (hand.getDurability() + 1);
            if (dura >= 1562) {
                hand.setType(Material.AIR);
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
                return;
            }

            hand.setDurability(dura);
            Clans.getCoreProtect().logRemoval(player.getName(), block.getLocation(), block.getType(), (byte) 0);
            block.breakNaturally();
        }
    }

    @Override
    public Perk getPerk() {
        return Perk.SUPERTOOLS;
    }
}