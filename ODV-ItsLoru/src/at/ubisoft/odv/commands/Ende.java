package at.ubisoft.odv.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.gamemanager.Countdown;
import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.main.Core;

public class Ende implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (p.hasPermission("ubi.admin")) {
			if (Core.gs == GameState.INGAME || Core.gs == GameState.PVPLESS) {
				Core.gs = GameState.END;
				p.sendMessage("§eItsLoru §8| §cDu hast das Spiel erfolgreich beendet.");
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage("§cDas Spiel wurde beendet.");
				Bukkit.broadcastMessage(" ");
				new Countdown().startEndGame();
			} else {
				p.sendMessage("§eItsLoru §8| §cEs ist derzeit kein guter Zeitpunkt dafür.");
			}
		} else {
			p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
		}
		
		return false;
	}

}
