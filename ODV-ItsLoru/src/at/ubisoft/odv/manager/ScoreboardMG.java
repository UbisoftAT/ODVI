package at.ubisoft.odv.manager;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.GameType;
import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.main.Core;

public class ScoreboardMG {
			
	public static void setLobbyScoreboard(Player p) {
	    ScoreboardManager b = Bukkit.getScoreboardManager();
	    Scoreboard sky = b.getNewScoreboard();
	    Objective objective = sky.registerNewObjective("test3", "dummy4");
	    
	    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	    
	    objective.setDisplayName("§8» §6§lONE§e§lDAY§b§lVARO §8▎  ");
	    
	    Score Score3 = objective.getScore("§7");
	    Score3.setScore(3);
	    
	    Score Score2 = objective.getScore("§8➝ §e§lGeplanter Start");
	    Score2.setScore(2);
	    
	    Score Score1 = objective.getScore("§8» §7" + Core.plannedstart);
	    Score1.setScore(1);
	    
	    Score Score0 = objective.getScore("§9");
	    Score0.setScore(0);
	    
	    p.setScoreboard(sky);
	}
		
  public static void setScoreboard(Player p) {
    ScoreboardManager b = Bukkit.getScoreboardManager();
    Scoreboard board = b.getNewScoreboard();
    Objective obj = board.registerNewObjective("test2", "dummy");
        
    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    
    obj.setDisplayName("§8» §6§lONE§e§lDAY§b§lVARO §8▎ ");
    
    Score Score16 = obj.getScore("§b");
    Score16.setScore(19);
    
    Score Score15 = obj.getScore("§eAktiv:");
    Score15.setScore(18);
    
    if (new GameType().isEnabled("bowless")) {
    	Score Score14 = obj.getScore("§8» §cBowLess");
        Score14.setScore(17);
  	}
	if (new GameType().isEnabled("cutclean")) {
		Score Score13 = obj.getScore("§8» §cCutClean");
		Score13.setScore(16);
	}
	if (new GameType().isEnabled("diamondless")) {
		Score Score12 = obj.getScore("§8» §cDiamondLess");
		Score12.setScore(15);
	}
	if (new GameType().isEnabled("horseless")) {
		Score Score11 = obj.getScore("§8» §cHorseLess");
		Score11.setScore(14);
	}
	if (new GameType().isEnabled("rodless")) {
		Score Score10 = obj.getScore("§8» §cRodLess");
        Score10.setScore(13);
	}
	if (new GameType().isEnabled("timebomb")) {
		Score Score9 = obj.getScore("§8» §cTimeBomb");
		Score9.setScore(12);
	}
	if (new GameType().isEnabled("vanillap")) {
		Score Score8 = obj.getScore("§8» §cVanillaPlus");
		Score8.setScore(11);
	}
	if (new GameType().isEnabled("fireless")) {
		Score Score7 = obj.getScore("§8» §cFireLess");
		Score7.setScore(10);
	}
	if (new GameType().isEnabled("backpack")) {
		Score Score6 = obj.getScore("§8» §cBackPack");
		Score6.setScore(9);
	}
	if (new GameType().isEnabled("ironless")) {
		Score Score5 = obj.getScore("§8» §cIronLess");
		Score5.setScore(8);
	}
	if (new GameType().isEnabled("limitations")) {
		Score Score4 = obj.getScore("§8» §cLimitationen");
		Score4.setScore(7);
	}
	if (new GameType().isEnabled("splitedlive")) {
		Score Score3 = obj.getScore("§8» §cSplitedLive");
		Score3.setScore(6);
	}
    
    Score Score2 = obj.getScore("§r");
    Score2.setScore(5);
    
    Score Score1 = obj.getScore("§2Verbleibend:");
    Score1.setScore(4);
    
    Score Score0 = obj.getScore("§8» §6" + new PlayerManager(p).getInGame().size());
    Score0.setScore(3); 
    
    Score Score001 = obj.getScore("§6");
    Score001.setScore(2); 
    
    Score Score02 = obj.getScore("§cBorder:");
    Score02.setScore(1);
    
    WorldBorder bo = LocationManager.getConfigLocation("Spawn.1", LocationManager.cfg).getWorld().getWorldBorder();
    Team border = board.registerNewTeam("border");
    border.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
    border.setPrefix("" + "§8» §e" + Integer.valueOf((int) bo.getSize()));
    obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(0);
    
    p.setScoreboard(board);
  }
  
  public static void updateScoreBoard(Player p) {

	  if (Core.gs == GameState.INGAME || Core.gs == GameState.PRE_GAME || Core.gs == GameState.PVPLESS) {
	  
      Scoreboard board = p.getScoreboard();
      WorldBorder bo = LocationManager.getConfigLocation("Spawn.1", LocationManager.cfg).getWorld().getWorldBorder();
      board.getTeam("border").setPrefix("" + "§8» §e" + Integer.valueOf((int) bo.getSize()));
	  }
  }
}
