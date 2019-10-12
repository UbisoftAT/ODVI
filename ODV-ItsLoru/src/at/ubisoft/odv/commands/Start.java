package at.ubisoft.odv.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.gamemanager.Countdown;
import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.main.Core;

public class Start implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (sender instanceof Player) {
			if (p.hasPermission("ubi.admin")) {
				if (Core.gs == GameState.LOBBY) {
					Core.gs = GameState.PRE_GAME;
					p.sendMessage("§eItsLoru §8| §aDu hast das Spiel erfolgreich gestartet.");
					new Countdown().startPre();
				} else {
					p.sendMessage("§eItsLoru §8| §cDas Spiel hat bereits gestartet.");
				}
			} else {
				p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte.");
			}
		} else {
			if (Core.gs == GameState.LOBBY) {
				Core.gs = GameState.PRE_GAME;
				sender.sendMessage("§eItsLoru §8| §aDu hast das Spiel erfolgreich gestartet.");
				new Countdown().startPre();
			} else {
				sender.sendMessage("§eItsLoru §8| §cDas Spiel hat bereits gestartet.");
			}
		}
		return false;
	}

}
