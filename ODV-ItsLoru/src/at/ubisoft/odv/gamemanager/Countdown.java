package at.ubisoft.odv.gamemanager;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.ScoreboardMG;
import at.ubisoft.odv.manager.WorldManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class Countdown {

	int countdown;
	int countdown2;
	public static int task;
	public static int task1;
	public static int task3;
	public static int task4;

	@SuppressWarnings("deprecation")
	public void startPre() {

		new WorldManager().teleport();
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.closeInventory();
			new PlayerManager(all).clear();
			if (!new PlayerManager(all).isInGame() && !all.hasPermission("ubi.admin")) {
				new PlayerManager(all).addToGame();
			}
			new WorldManager().setDifficulty(Difficulty.PEACEFUL);
			all.setGameMode(GameMode.ADVENTURE);
			all.setHealth(20);
			new GameType().getDaySettings();
			updateScoreboard();
			all.sendTitle("§eMinecraft ODV", "");
			((CraftServer) Bukkit.getServer()).getServer().setMotd("PreGame");
			all.sendTitle("§cStart in 60 Sekunden.", "");
			ScoreboardMG.setScoreboard(all);
		}
		startPreGame();

		Core.gs = GameState.PRE_GAME;
	}

	public void startPreGame() {

		countdown = 61;

		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, () -> {

			if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
				return;
			}

			countdown--;

			if (countdown == 0) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "forcestart");
				stop(task);
			}

			if (countdown == 60) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel startet in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStart in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 30) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel startet in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStart in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 15) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel startet in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStart in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 10) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel startet in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStart in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown > 1 && countdown < 10) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel startet in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStart in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 1) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel startet in §eeiner §aSekunde.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStart in", "§e" + countdown + "§a Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			}
			if (countdown < 61) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.setLevel(countdown);
				}
			}
		}, 20, 20);
	}

	@SuppressWarnings("deprecation")
	public void startPvpTimer() {

		countdown2 = 16;

		task = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Core.instance, () -> {

			if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
				return;
			}

			countdown2--;

			if (countdown2 == 0) {
				stop();
				Core.gs = GameState.INGAME;
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wurde aktiviert.");
				Bukkit.broadcastMessage(" ");
			}

			if (countdown2 == 10) {
				Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wird in §e" + countdown2 + " §cMinuten aktiviert.");
			} else if (countdown2 == 5) {
				Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wird in §e" + countdown2 + " §cMinuten aktiviert.");
			} else if (countdown2 == 3) {
				Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wird in §e" + countdown2 + " §cMinuten aktiviert.");
			} else if (countdown2 == 2) {
				Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wird in §e" + countdown2 + " §cMinuten aktiviert.");
			} else if (countdown2 == 1) {
				Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wird in §e60 Sekunden §caktiviert.");
			}
		}, 20 * 60, 20 * 60);
	}

	public void startEndGame() {

		countdown = 61;

		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, () -> {

			if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
				return;
			}

			countdown--;

			if (countdown == 0) {
				stop(task);
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.kickPlayer("§cSpiel wurde beendet.");
				}
				Core.gs = GameState.END;
			}

			if (countdown == 60) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel stoppt in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStop in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 30) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel stoppt in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStop in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 15) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel stoppt in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStop in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 10) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel stoppt in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStop in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown > 1 && countdown < 10) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel stoppt in §e" + countdown + "§a Sekunden.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStop in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			} else if (countdown == 1) {
				Bukkit.broadcastMessage("§eItsLoru §8| §aDas Spiel stoppt in §eeiner §aSekunde.");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cStop in", "§e" + countdown + " Sekunden");
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 1);
				}
			}
			if (countdown < 61) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					all.setLevel(countdown);
				}
			}
		}, 20, 20);
	}

	public void updateScoreboard() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {

			@Override
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					ScoreboardMG.updateScoreBoard(all);
				}
			}
		}, 1, 1);
	}

	@SuppressWarnings("deprecation")
	public void updateWhitelist() {
		task1 = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Core.instance, new Runnable() {

			@Override
			public void run() {
				Date d = new Date();

				if (Core.gs != GameState.LOBBY) {
					stop(task1);
				}

				if (d.getHours() == Integer.valueOf(Core.allowedjoin1) && Integer.valueOf(Core.allowedjoin2) > d.getMinutes()) {
					Bukkit.setWhitelist(false);
					Bukkit.broadcastMessage("§cDie Whitelist ist nun aus.");
					stop(task1);
				}
			}
		}, 20, 20);
	}
	
	public void autoStart() {
		task4 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				Date d = new Date();
								
				if (Core.gs != GameState.LOBBY) {
					stop(task4);
				}

				if (d.getHours() == Integer.valueOf(Core.start1) && Integer.valueOf(Core.start2) == d.getMinutes()) {
					startPre();
					stop(task4);
				}
			}
		}, 20, 20);
	}

	public void stop(int task) {
		Bukkit.getScheduler().cancelTask(task);
	}
	
	public void setState(GameState s) {
		Core.gs = s;
	}

	public void stop() {
		Bukkit.getScheduler().cancelTask(task);
	}

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title,
			String subtitle) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

		PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null,
				fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
		connection.sendPacket(packetPlayOutTimes);
		if (subtitle != null) {
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(
					PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
			connection.sendPacket(packetPlayOutSubTitle);
		}
		if (title != null) {
			title = ChatColor.translateAlternateColorCodes('&', title);
			IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
			PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
					titleMain);
			connection.sendPacket(packetPlayOutTitle);
		}
	}
}
