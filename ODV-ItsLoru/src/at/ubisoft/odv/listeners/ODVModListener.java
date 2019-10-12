package at.ubisoft.odv.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import at.ubisoft.odv.commands.BackPack;
import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.GameType;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.ScoreboardMG;

public class ODVModListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
			try {
			if (e.getInventory().getName().equalsIgnoreCase("§eModules")) {
				e.setCancelled(true);
			String current = e.getCurrentItem().getItemMeta().getDisplayName();
			if (current.equalsIgnoreCase("§eBogenlos")) {
				if (!new GameType().isEnabled("bowless")) {
					new GameType().enable("bowless");
				} else {
					new GameType().disable("bowless");
				}
			} else if (current.equalsIgnoreCase("§eCutClean")) {
				if (!new GameType().isEnabled("cutclean")) {
					new GameType().enable("cutclean");
				} else {
					new GameType().disable("cutclean");
				}
			} else if (current.equalsIgnoreCase("§eDiamantenLos")) {
				if (!new GameType().isEnabled("diamondless")) {
					new GameType().enable("diamondless");
				} else {
					new GameType().disable("diamondless");
				}
			} else if (current.equalsIgnoreCase("§ePferdeLos")) {
				if (!new GameType().isEnabled("horseless")) {
					new GameType().enable("horseless");
				} else {
					new GameType().disable("horseless");
				}
			} else if (current.equalsIgnoreCase("§eRodLess")) {
				if (!new GameType().isEnabled("rodless")) {
					new GameType().enable("rodless");
				} else {
					new GameType().disable("rodless");
				}
			} else if (current.equalsIgnoreCase("§eZeitBombe")) {
				if (!new GameType().isEnabled("timebomb")) {
					new GameType().enable("timebomb");
				} else {
					new GameType().disable("timebomb");
				}
			} else if (current.equalsIgnoreCase("§eVanillaPlus")) {
				if (!new GameType().isEnabled("vanillap")) {
					new GameType().enable("vanillap");
				} else {
					new GameType().disable("vanillap");
				}
			} else if (current.equalsIgnoreCase("§eFeuerLos")) {
				if (!new GameType().isEnabled("fireless")) {
					new GameType().enable("fireless");
				} else {
					new GameType().disable("fireless");
				}
			} else if (current.equalsIgnoreCase("§eBackPack")) {
				if (!new GameType().isEnabled("backpack")) {
					new GameType().enable("backpack");
				} else {
					new GameType().disable("backpack");
				}
			} else if (current.equalsIgnoreCase("§eEisenLos")) { 
				if (!new GameType().isEnabled("ironless")) {
					new GameType().enable("ironless");
				} else {
					new GameType().disable("ironless");
				}
			} else if (current.equalsIgnoreCase("§eLimitationen")) {
				if (!new GameType().isEnabled("limitations")) {
					new GameType().enable("limitations");
				} else {
					new GameType().disable("limitations");
				}
			} else if (current.equalsIgnoreCase("§eGespaltetes Leben")) {
				if (!new GameType().isEnabled("splitedlive")) {
					new GameType().enable("splitedlive");
				} else {
					new GameType().disable("splitedlive");
				}
			}
			p.closeInventory();
			p.performCommand("mod gui");
			
			if (Core.gs == GameState.PRE_GAME || Core.gs == GameState.INGAME || Core.gs == GameState.PVPLESS) {
				for (Player all : Bukkit.getOnlinePlayers()) {
					ScoreboardMG.setScoreboard(all);
				}
			}
				}
			} catch (NullPointerException ex) {}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§eBackPack")) {
			BackPack.bp.put((Player) e.getPlayer(), e.getInventory().getContents());
		}
	}

}
