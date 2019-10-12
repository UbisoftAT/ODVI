package at.ubisoft.odv.main;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import at.ubisoft.odv.commands.AdminPlaying;
import at.ubisoft.odv.commands.Alive;
import at.ubisoft.odv.commands.BackPack;
import at.ubisoft.odv.commands.Ende;
import at.ubisoft.odv.commands.Fly;
import at.ubisoft.odv.commands.ForceStart;
import at.ubisoft.odv.commands.GameMaster;
import at.ubisoft.odv.commands.GameModes;
import at.ubisoft.odv.commands.Mod;
import at.ubisoft.odv.commands.ODV;
import at.ubisoft.odv.commands.Set;
import at.ubisoft.odv.commands.Spec;
import at.ubisoft.odv.commands.Start;
import at.ubisoft.odv.commands.StopCountdown;
import at.ubisoft.odv.commands.Teleport;
import at.ubisoft.odv.commands.TeleportHere;
import at.ubisoft.odv.commands.Warp;
import at.ubisoft.odv.gamemanager.Countdown;
import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.GameType;
import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.listeners.ODVGameMasterListener;
import at.ubisoft.odv.listeners.ODVModListener;
import at.ubisoft.odv.listeners.ODVProtection;
import at.ubisoft.odv.manager.LocationManager;
import at.ubisoft.odv.manager.ODVConfig;
import at.ubisoft.odv.manager.WorldManager;

public class Core extends JavaPlugin {
	
	public static GameState gs;
	public static Core instance;
	
	public static String plannedstart = new ODVConfig().getString("PlannedTime");
	public static String allowedjoin1 = new ODVConfig().getString("AllowedPlayerJoinTime.Stunde");
	public static String allowedjoin2 = new ODVConfig().getString("AllowedPlayerJoinTime.Minute");
	public static String start1 = new ODVConfig().getString("StartTime.Stunde");
	public static String start2 = new ODVConfig().getString("StartTime.Minute");
    int task;
	
	public void onEnable() {
		
		instance = this;
		gs = GameState.LOBBY;
				
		Bukkit.getPluginManager().registerEvents(new ODVProtection(), this);
		Bukkit.getPluginManager().registerEvents(new ODVModListener(), this);
		Bukkit.getPluginManager().registerEvents(new ODVGameMasterListener(), this);
		Bukkit.setWhitelist(true);

		new ODVConfig().create();
		new GameType().getDaySettings();
		
		for (World w : Bukkit.getWorlds()) {
			WorldBorder b = w.getWorldBorder();
			b.setCenter(LocationManager.getConfigLocation("Lobby", LocationManager.cfg));
			b.setSize(10);
			b.setSize(2000, 60);
			b.setDamageAmount(5.0);
		}
		
		for (Player all : Bukkit.getOnlinePlayers()) {
			new PlayerManager(all).addToGame();
		}
		
		new Countdown().updateWhitelist();
		new Countdown().autoStart();

		new WorldManager().setDifficulty(Difficulty.PEACEFUL);
		
		Bukkit.setDefaultGameMode(GameMode.SURVIVAL);
		
		getCommand("gamemode").setExecutor(new GameModes());
		getCommand("set").setExecutor(new Set());
		getCommand("warp").setExecutor(new Warp());
		getCommand("start").setExecutor(new Start());
		getCommand("forcestart").setExecutor(new ForceStart());
		getCommand("adminplaying").setExecutor(new AdminPlaying());
		getCommand("spectate").setExecutor(new Spec());
		getCommand("mod").setExecutor(new Mod());
		getCommand("stopcountdown").setExecutor(new StopCountdown());
		getCommand("fly").setExecutor(new Fly());
		getCommand("alive").setExecutor(new Alive());
		getCommand("teleport").setExecutor(new Teleport());
		getCommand("teleporthere").setExecutor(new TeleportHere());
		getCommand("gamemaster").setExecutor(new GameMaster());
		getCommand("backpack").setExecutor(new BackPack());
		getCommand("ende").setExecutor(new Ende());
		getCommand("odv").setExecutor(new ODV());

	}
	
	public void onDisable() {}
	

}
