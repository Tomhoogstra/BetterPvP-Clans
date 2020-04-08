package net.betterpvp.clans;


import net.betterpvp.clans.clans.commands.ClanCommand;
import net.betterpvp.clans.clans.listeners.*;

import net.betterpvp.clans.classes.DamageManager;
import net.betterpvp.clans.classes.Energy;
import net.betterpvp.clans.classes.RoleManager;
import net.betterpvp.clans.classes.menu.KitListener;
import net.betterpvp.clans.combat.CombatManager;
import net.betterpvp.clans.combat.LogManager;
import net.betterpvp.clans.combat.throwables.ThrowableManager;
import net.betterpvp.clans.dailies.QuestManager;
import net.betterpvp.clans.dailies.perks.QuestPerkManager;
import net.betterpvp.clans.economy.shops.ShopCommand;
import net.betterpvp.clans.economy.shops.ShopEntities;
import net.betterpvp.clans.economy.shops.ShopManager;
import net.betterpvp.clans.economy.shops.menu.buttons.ShopListener;
import net.betterpvp.clans.economy.shops.nms.ShopSkeleton;
import net.betterpvp.clans.economy.shops.nms.ShopVillager;
import net.betterpvp.clans.economy.shops.nms.ShopZombie;
import net.betterpvp.clans.economy.shops.nms.UtilShop;
import net.betterpvp.clans.effects.EffectManager;
import net.betterpvp.clans.farming.FarmingListener;
import net.betterpvp.clans.fields.management.FieldsManager;
import net.betterpvp.clans.fishing.FishingListener;
import net.betterpvp.clans.fun.BounceListener;
import net.betterpvp.clans.gamer.Gamer;
import net.betterpvp.clans.gamer.GamerConnectionListener;
import net.betterpvp.clans.gamer.GamerManager;
import net.betterpvp.clans.gamer.GamerRepository;
import net.betterpvp.clans.general.WorldListener;
import net.betterpvp.clans.general.commands.FindCommand;
import net.betterpvp.clans.koth.KOTHManager;
import net.betterpvp.clans.mysql.ReflectionsUtil;
import net.betterpvp.clans.scoreboard.ScoreboardManager;
import net.betterpvp.clans.settings.Options;
import net.betterpvp.clans.skills.selector.SelectorManager;
import net.betterpvp.clans.skills.selector.SkillListener;
import net.betterpvp.clans.skills.selector.skills.data.CustomArmorStand;
import net.betterpvp.clans.weapon.WeaponManager;
import net.betterpvp.clans.worldevents.WEManager;
import net.betterpvp.clans.worldevents.types.nms.*;
import net.betterpvp.core.command.CommandManager;
import net.betterpvp.core.configs.ConfigManager;
import net.betterpvp.core.framework.CoreLoadedEvent;
import net.betterpvp.core.utility.UtilFormat;
import net.betterpvp.core.utility.UtilMessage;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;
import net.minecraft.server.v1_15_R1.*;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Clans extends JavaPlugin implements Listener {

    private static Options options = null;
    private ConfigManager config;
    private boolean hasStarted;

    @Override
    public void onEnable() {

        config = new ConfigManager(this);
        options = new Options(this);
        Bukkit.getPluginManager().registerEvents(this, this);



    }

    private void load() {

        UtilShop.registerEntity("Zombie",  54, EntityZombie.class, ShopZombie.class);
        UtilShop.registerEntity("Spider",  52, EntitySpider.class, BossSpider.class);
        UtilShop.registerEntity("CaveSpider",  59, EntityCaveSpider.class, BossCaveSpider.class);
        UtilShop.registerEntity("Skeleton",  51, EntitySkeleton.class, ShopSkeleton.class);
        UtilShop.registerEntity("Zombie",  54, EntityZombie.class, BossZombie.class);
        UtilShop.registerEntity("Wither",  64, EntityWither.class, BossWither.class);
        UtilShop.registerEntity("Skeleton",  51, EntitySkeleton.class, BossSkeleton.class);
        UtilShop.registerEntity("Villager",  120, EntityVillager.class, ShopVillager.class);
        UtilShop.registerEntity("ArmorStand",  30, EntityArmorStand.class, CustomArmorStand.class);

        ReflectionsUtil.loadRepositories("net.betterpvp.clans", this);
        ReflectionsUtil.registerCommands("net.betterpvp.clans", this);
        ReflectionsUtil.loadRecipes("net.betterpvp.clans");

        startTimers();

        new SkillListener(this);
        new SelectorManager(this);
        new DamageManager(this);
        new RoleManager(this);
        new CombatManager(this);
        new WeaponManager(this);
        new WEManager(this);
        new GamerConnectionListener(this);

        new EffectManager(this);
        new ThrowableManager(this);
        new ChatListener(this);
        new ClanEventListener(this);
        new ClanMenuListener(this);
        // new ClanScoreboardListener(this);
        new DamageListener(this);
        new EnergyListener(this);
        new ExplosionListener(this);
        new InsuranceListener(this);
        new InteractListener(this);
        new InviteHandler(this);
        new MovementListener(this);
        new PillageListener(this);
        new Energy(this);
        new BounceListener(this);
        new WorldListener(this);
        new ShopEntities(this);
        new ShopListener(this);
        new ShopManager(this);
        new QuestPerkManager();
        new FieldsManager(this);
        new ScoreboardManager(this);
        new KitListener(this);
        new QuestManager(this);
        new FishingListener(this);
        new FarmingListener(this);
        new KOTHManager(this);
        new ClanScoreboardListener(this);

        CommandManager.addCommand(new ShopCommand(this));
        CommandManager.addCommand(new FindCommand(this));

        getCommand("clan").setExecutor(new ClanCommand(this));


        hasStarted = true;

    }

    @EventHandler
    public void onLoad(CoreLoadedEvent e){
        System.out.println("Core loaded, beginning Clans load.");
        load();
    }

    private static Plugin plugin = Bukkit.getPluginManager().getPlugin("CoreProtect");

    public static CoreProtectAPI getCoreProtect() {


        // Check that CoreProtect is loaded
        if (plugin == null || !(plugin instanceof CoreProtect)) {
            return null;
        }

        // Check that the API is enabled
        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
        if (CoreProtect.isEnabled() == false) {
            return null;
        }

        // Check that a compatible version of the API is loaded
        if (CoreProtect.APIVersion() < 4) {
            return null;
        }

        return CoreProtect;
    }



    private void startTimers(){
        new BukkitRunnable(){
            @Override
            public void run() {

                LogManager.processLogs();

            }
        }.runTaskTimerAsynchronously(this, 0L, 2L);

        new BukkitRunnable(){
            @Override
            public void run(){
                for(Player p : Bukkit.getOnlinePlayers()){
                    Gamer gamer = GamerManager.getOnlineGamer(p);
                    GamerRepository.updateGamer(gamer);

                }
                System.out.println("Updated player data!");
            }
        }.runTaskTimerAsynchronously(this, 6000L, 6000L);

        new BukkitRunnable(){


            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    Gamer gamer = GamerManager.getOnlineGamer(p);
                    if(gamer != null){


                        double add = 0;

                        // TODO reimplement MAH bonus
                       /* double multiplier = MAHManager.isAuthenticated(p) ? 3 : 1.0;
                        if(MAHManager.isAuthenticated(p)) {
                            add += 1000;
                        }

                        if(c.isDiscordLinked()) {
                            add += 1000;
                        }*/

                        gamer.addCoins((getOptions().getOnlineReward() + add) / 2);

                        gamer.addFragments(2);

                        UtilMessage.message(p, "Online Reward", "You received " + ChatColor.YELLOW + "$"
                                + UtilFormat.formatNumber((int) ((getOptions().getOnlineReward() + add) /2)) + ChatColor.GRAY + " Coins.");

                        UtilMessage.message(p, "Online Reward", "You received " + ChatColor.YELLOW + (2) + ChatColor.GRAY + " fragments");
                    }
                }

            }
        }.runTaskTimer(this, 72000 /2, 72000 /2);
    }



    public static Options getOptions() {
        return options;
    }

    public ConfigManager getConfigManager() {
        return config;
    }

    public boolean hasStarted(){
        return hasStarted;
    }
}