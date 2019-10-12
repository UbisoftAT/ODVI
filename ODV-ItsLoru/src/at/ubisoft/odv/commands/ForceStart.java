package at.ubisoft.odv.commands;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import at.ubisoft.odv.gamemanager.Countdown;
import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.UUIDFetcher;
import at.ubisoft.odv.manager.WorldManager;

public class ForceStart implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (p.hasPermission("ubi.admin")) {
				if (Core.gs == GameState.LOBBY) {
					new Countdown().stop();
					Core.gs = GameState.PRE_GAME;
					p.sendMessage("§eItsLoru §8| §aDu hast das Spiel erfolgreich gestartet.");
					new Countdown().startPre();
				} else if (Core.gs == GameState.PRE_GAME) {
					new Countdown().stop();
					Core.gs = GameState.PVPLESS;
					for (Player ingame : new PlayerManager(null).getInGame()) {
						new PlayerManager(ingame).setName("§e" + UUIDFetcher.getName(ingame.getUniqueId()));
						ingame.setGameMode(GameMode.SURVIVAL);
					}
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wird in 15 Minuten aktiviert.");
					Bukkit.broadcastMessage(" ");
					for (Player all : Bukkit.getOnlinePlayers()) {
						all.setGameMode(GameMode.SURVIVAL);
						all.closeInventory();
						all.sendTitle("§eMinecraft ODV", "§7Es beginnt...");
						new WorldManager().startWorldBoarder();
						all.setExp(0);
						all.playSound(all.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);
						new WorldManager().setDifficulty(Difficulty.NORMAL);
						new PlayerManager(all).clear();
						all.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 15));
						if (!new PlayerManager(all).isInGame() && !all.hasPermission("ubi.admin")) {
							new PlayerManager(all).addToGame();
						}

						((CraftServer) Bukkit.getServer()).getServer().setMotd("InGame");
					}
					p.sendMessage("§eItsLoru §8| §aDu hast das Spiel erfolgreich gestartet.");
					new Countdown().startPvpTimer();
				} else if (Core.gs == GameState.INGAME || Core.gs == GameState.PVPLESS || Core.gs == GameState.END) {
					p.sendMessage("§eItsLoru §8| §cDas Spiel hat bereits gestartet");
				}
			} else {
				p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
			}
		} else {
			new Countdown().stop();
			Core.gs = GameState.PVPLESS;
			for (Player ingame : new PlayerManager(null).getInGame()) {
				new PlayerManager(ingame).setName("§e" + UUIDFetcher.getName(ingame.getUniqueId()));

			}
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage("§eItsLoru §8| §cPVP wird in §e15 §cMinuten aktiviert.");
			Bukkit.broadcastMessage(" ");
			for (Player all : Bukkit.getOnlinePlayers()) {
				all.closeInventory();
				all.setGameMode(GameMode.SURVIVAL);
				all.sendTitle("§eMinecraft ODV", "§7Es beginnt...");
				new WorldManager().startWorldBoarder();
				all.setExp(0);
				all.playSound(all.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);
				new WorldManager().setDifficulty(Difficulty.NORMAL);
				new PlayerManager(all).clear();
				all.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 15));
				if (!new PlayerManager(all).isInGame() && !all.hasPermission("ubi.admin")) {
					new PlayerManager(all).addToGame();
				}
				new Countdown().startPvpTimer();

				((CraftServer) Bukkit.getServer()).getServer().setMotd("InGame");
			}
		}
		return false;
	}

}
