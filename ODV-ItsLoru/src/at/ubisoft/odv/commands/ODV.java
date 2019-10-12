package at.ubisoft.odv.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.ScoreboardMG;

public class ODV implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (p.hasPermission("ubi.admin")) {
			if (args.length == 0) {
				p.sendMessage("§8§m---------------");
				p.sendMessage("§8» §e/odv status");
				p.sendMessage("§8» §e/odv reloadsb");
				p.sendMessage("§8§m---------------");
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("status")) {
					p.sendMessage("§8§m---------------");
					p.sendMessage("§8» §eStandpunkt: " + Core.gs);
					p.sendMessage("§8§m---------------");
				} else if (args[0].equalsIgnoreCase("reloadsb")) {
					p.sendMessage("§eItsLoru §8| §aErfolgreich.");
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (Core.gs == GameState.LOBBY) {
							ScoreboardMG.setLobbyScoreboard(all);
						} else {
							ScoreboardMG.setScoreboard(all);
						}
					}
				}
			}
		} else {
			p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
		}
		return false;
	}

}
