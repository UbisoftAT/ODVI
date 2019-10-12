package at.ubisoft.odv.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.main.Core;

public class Alive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (Core.gs == GameState.INGAME || Core.gs == GameState.PRE_GAME || Core.gs == GameState.INGAME || Core.gs == GameState.PVPLESS) {
		
		p.sendMessage("§8§m------------------");
		p.sendMessage("§8» §eVerbleibende Spieler: §c" + new PlayerManager(p).getInGame().size());
		p.sendMessage(" ");
		for (Player all : new PlayerManager(p).getInGame()) {
		p.sendMessage("§8» §e" + all.getName()); 
		}
		p.sendMessage("§8§m------------------");
		
		} else {
			p.sendMessage("§eItsLoru §8| §cDas Spiel hat noch nicht begonnen oder ist bereits vorbei.");
		}
		
		return false;
	}

}
