package at.ubisoft.odv.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportHere implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player)sender;
		
		if (p.hasPermission("ubi.admin")) {
			
			if (args.length == 0) {
				p.sendMessage("§8§m-----------------");
				p.sendMessage("§8» §e/tphere [Name]");
				p.sendMessage("§8§m-----------------");
			} else if (args.length == 1) {
				if (Bukkit.getPlayer(args[0]).isOnline()) {
					Player target = Bukkit.getPlayer(args[0]);
					target.teleport(p);
					target.sendMessage("§eItsLoru §8| §cTeleportation...");
					p.sendMessage("§eItsLoru §8| §aDu hast erfolgreich §e" + target.getName() + " §azu dich teleportiert.");
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
