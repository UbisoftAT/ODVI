package at.ubisoft.odv.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {

	private ArrayList<Player> fly = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (p.hasPermission("ubi.team")) {
			if (fly.contains(p)) {
				fly.remove(p);
				p.setFlying(false);
				p.setAllowFlight(false);
				p.sendMessage("§eItsLoru §8| §cFly (inaktiv)");
			} else {
				fly.add(p);
				p.setAllowFlight(true);
				p.setFlying(true);
				p.sendMessage("§eItsLoru §8| §cFly (aktiv)");
			}
		} else {
			p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte.");
		}

		return false;
	}

}
