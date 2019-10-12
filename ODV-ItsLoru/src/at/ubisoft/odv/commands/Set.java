package at.ubisoft.odv.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.LocationManager;
import at.ubisoft.odv.manager.ScoreboardMG;

public class Set implements CommandExecutor {

	int playerspawns;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("set")) {
			if (sender instanceof Player) {

				Player p = (Player) sender;
				if (p.hasPermission("ubi.admin")) {
					
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("PlannedTime")) {
							if (Core.gs == GameState.LOBBY) {
							Core.plannedstart = args[1];
							p.sendMessage("§eItsLoru §8| §aErfolgreich.");
							for (Player all : Bukkit.getOnlinePlayers()) {
								ScoreboardMG.setLobbyScoreboard(all);
								}
							} else {
								p.sendMessage("§eItsLoru §8| §cDas Spiel hat bereits gestartet.");
							}
						}
					}

					else if (args.length == 1) {

						if (args[0].equalsIgnoreCase("Spawn")) {
							playerspawns++;
							LocationManager.createConfigLocation(p.getLocation(), "Spawn." + playerspawns, LocationManager.file, LocationManager.cfg);
							p.sendMessage("§eItsLoru §8| §aErfolgreich.");
							return true;
						}

						if (LocationManager.cfg.getString(args[0]) == null) {

							LocationManager.createConfigLocation(p.getLocation(), args[0], LocationManager.file, LocationManager.cfg);
							p.sendMessage("§eItsLoru §8| §aDu hast den Warp §e" + args[0] + " §aerfolgreich gesetzt!");
						} else {

							p.sendMessage("§eItsLoru §8| §cDieser Warp existiert bereits!");
						}
					} else {

						p.sendMessage("§eItsLoru §8| §cSyntax: /set [Warp]");
					}
				} else {

					p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
				}

			} else {

				sender.sendMessage("§eItsLoru §8| §cDu darfst keine Warps setzen!");
			}
		}
		return false;
	}
}
