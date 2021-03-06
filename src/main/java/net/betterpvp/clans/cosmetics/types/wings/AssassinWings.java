package net.betterpvp.clans.cosmetics.types.wings;

import net.betterpvp.clans.Clans;
import net.betterpvp.clans.cosmetics.CosmeticType;
import net.betterpvp.core.particles.data.color.RegularColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class AssassinWings extends CosmeticWings{

    public AssassinWings(Clans clans) {
        super(clans);
    }

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.WINGS;
    }

    @Override
    void run(Player player, List<Player> playerList) {
        if (!canShow(player)) return;

        Location loc = player.getLocation().clone();
        loc.setPitch(0.0F);
        loc.add(0.0D, 0.0D, 0.0D);
        loc.add(loc.getDirection().multiply(-0.2D));
        loc.setYaw(loc.getYaw() + 75);
        Location loc2 = loc.clone();

        display(loc, loc2, new RegularColor(94, 57, 33), new RegularColor(94, 57, 33),
                new RegularColor(94, 57, 33), playerList);

        loc = player.getLocation().clone();
        loc.setPitch(0.0F);
        loc.add(0.0D, 0.0D, 0.0D);
        loc.add(loc.getDirection().multiply(-0.2D));
        loc.setYaw(loc.getYaw() - 75);
        loc2 = loc.clone();
        display(loc, loc2, new RegularColor(94, 57, 33), new RegularColor(94, 57, 33),
                new RegularColor(94, 57, 33), playerList);
    }

    @Override
    public String getName() {
        return "AssassinWings";
    }

    @Override
    public String getDisplayName() {
        return "Assassin Wings";
    }

    @Override
    public long getExpiryTime() {
        return 0;
    }
}
