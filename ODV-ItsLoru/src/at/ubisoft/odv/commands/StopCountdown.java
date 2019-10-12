package at.ubisoft.odv.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.gamemanager.Countdown;

public class StopCountdown implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player)sender;
		
		if (p.hasPermission("ubi.admin")) {
			if (args.length == 0) {
				new Countdown().stop();
				p.sendMessage(" ");
				p.sendMessage("§eeUm zu dem alten Standpunkt zurück zu musst du §c/forcestart machen.");
				p.sendMessage(" ");
			}
		} else {
			p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
		}
		
		return false;
	}

}
