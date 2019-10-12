package at.ubisoft.odv.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (p.hasPermission("ubi.team")) {
			if (args.length == 0) {
				p.sendMessage("§eItsLoru §8| §cSyntax: /gm [0/1/2/3]");
				p.sendMessage("§eItsLoru §8| §cSyntax: /gm [0/1/2/3] [Spieler]");
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("0")) {
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + p.getGameMode() + " §aModus.");
				} else if (args[0].equalsIgnoreCase("1")) {
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + p.getGameMode() + " §aModus.");
				} else if (args[0].equalsIgnoreCase("2")) {
					p.setGameMode(GameMode.ADVENTURE);
					p.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + p.getGameMode() + " §aModus.");
				} else if (args[0].equalsIgnoreCase("3")) {
					p.setGameMode(GameMode.SPECTATOR);
					p.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + p.getGameMode() + " §aModus.");
				}
			} else if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[1]);
				if (target != null && target.isOnline()) {
					if (args[0].equalsIgnoreCase("0")) {
						target.setGameMode(GameMode.SURVIVAL);
						p.sendMessage("§eItsLoru §8| §aEr ist nun im §e" + target.getGameMode() + " §aModus.");
						target.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + target.getGameMode() + " §aModus.");
					} else if (args[0].equalsIgnoreCase("1")) {
						target.setGameMode(GameMode.CREATIVE);
						p.sendMessage("§eItsLoru §8| §aEr ist nun im §e" + target.getGameMode() + " §aModus.");
						target.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + target.getGameMode() + " §aModus.");
					} else if (args[0].equalsIgnoreCase("2")) {
						target.setGameMode(GameMode.ADVENTURE);
						p.sendMessage("§eItsLoru §8| §aEr ist nun im §e" + target.getGameMode() + " §aModus.");
						target.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + target.getGameMode() + " §aModus.");
					} else if (args[0].equalsIgnoreCase("3")) {
						target.setGameMode(GameMode.SPECTATOR);
						p.sendMessage("§eItsLoru §8| §aEr ist nun im §e" + target.getGameMode() + " §aModus.");
						target.sendMessage("§eItsLoru §8| §aDu bist nun im §e" + target.getGameMode() + " §aModus.");
					}
				} else {
					p.sendMessage("§eItsLoru §8| §cDieser Spieler ist Offline.");
				}
			}
		} else {
			p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
		}
		
		return false;
	}

}
