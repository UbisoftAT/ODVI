package at.ubisoft.odv.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.ScoreboardMG;

public class AdminPlaying implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (p.hasPermission("ubi.admin")) {
			if (Core.gs == GameState.LOBBY) {
			if (!new PlayerManager(p).isInGame()) {
					new PlayerManager(p).addToGame();
					p.sendMessage("§eItsLoru §8| §aDu nimmst nun am Spiel teil.");
					p.setGameMode(GameMode.SURVIVAL);
					new PlayerManager(p).clear();
					ScoreboardMG.setLobbyScoreboard(p);
					p.performCommand("warp Lobby");
					p.setHealth(20);
					p.setFoodLevel(20);
				
			} else {
				p.sendMessage("§eItsLoru §8| §aDu nimmst nun nicht mehr am Spiel teil.");
				new PlayerManager(p).clear();
				new PlayerManager(p).removeFromGame();
				ScoreboardMG.setLobbyScoreboard(p);
				}
			} else {
			p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
			}
		} else {
			p.sendMessage("§eItsLoru §8| §cDas Spiel hat bereits begonnen.");
		}
		
		return false;
	}

}
