package net.betterpvp.clans.clans.map;

import io.github.bananapuncher714.cartographer.core.api.WorldCursor;
import io.github.bananapuncher714.cartographer.core.api.map.WorldCursorProvider;
import io.github.bananapuncher714.cartographer.core.map.Minimap;
import io.github.bananapuncher714.cartographer.core.renderer.PlayerSetting;
import net.betterpvp.clans.Clans;
import net.betterpvp.clans.clans.Alliance;
import net.betterpvp.clans.clans.Clan;
import net.betterpvp.clans.clans.ClanMember;
import net.betterpvp.clans.clans.ClanUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCursor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClanCursorProvider implements WorldCursorProvider {
    @Override
    public Collection<WorldCursor> getCursors(Player player, Minimap minimap, PlayerSetting playerSetting) {
        Set<WorldCursor> cursors = new HashSet<>();
        cursors.add(new WorldCursor(playerSetting.getLocation(), MapCursor.Type.WHITE_POINTER));

        if (playerSetting.getScale() >= 4) {
            cursors.add(new WorldCursor(ChatColor.WHITE + "Red Spawn", Clans.getOptions().getRedSpawnLoc(), MapCursor.Type.BANNER_RED));
            cursors.add(new WorldCursor(ChatColor.WHITE + "Red Shop", Clans.getOptions().getRedShopLoc(), MapCursor.Type.BANNER_RED));
            cursors.add(new WorldCursor(ChatColor.WHITE + "Blue Spawn", Clans.getOptions().getBlueSpawnLoc(), MapCursor.Type.BANNER_BLUE));
            cursors.add(new WorldCursor(ChatColor.WHITE + "Blue Shop", Clans.getOptions().getBlueShopLoc(), MapCursor.Type.BANNER_BLUE));
        }

        Clan clan = ClanUtilities.getClan(player);
        if (clan != null) {
            for (ClanMember member : clan.getMembers()) {
                Player pMember = Bukkit.getPlayer(member.getUUID());
                if (pMember != null) {
                    if (player.equals(pMember)) continue;
                    cursors.add(new WorldCursor(ChatColor.AQUA + pMember.getName(), pMember.getLocation(), MapCursor.Type.BLUE_POINTER, true));
                }
            }

            if (!clan.getAlliances().isEmpty()) {
                for (Alliance alliance : clan.getAlliances()) {
                    Clan cAlly = alliance.getClan();
                    for (ClanMember allyMember : cAlly.getMembers()) {
                        Player allyPlayer = Bukkit.getPlayer(allyMember.getUUID());
                        if (allyPlayer != null) {
                            cursors.add(new WorldCursor((alliance.hasTrust() ? ChatColor.DARK_GREEN : ChatColor.GREEN) + allyPlayer.getName(),
                                    allyPlayer.getLocation(), MapCursor.Type.GREEN_POINTER, true));
                        }
                    }
                }
            }
        }
        return cursors;
    }
}
