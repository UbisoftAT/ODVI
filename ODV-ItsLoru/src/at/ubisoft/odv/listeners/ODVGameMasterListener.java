package at.ubisoft.odv.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import at.ubisoft.odv.manager.WorldManager;

public class ODVGameMasterListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			String name = e.getCurrentItem().getItemMeta().getDisplayName();

			if (name.equalsIgnoreCase("§cGameMaster")) {
				e.setCancelled(true);
			}
			
			if (name.equalsIgnoreCase("§cCleare Wetter")) {
				e.setCancelled(true);
				new WorldManager().clearWeather();
			} else if (name.equalsIgnoreCase("§cSetze Tag")) {
				e.setCancelled(true);
				new WorldManager().setDay();
			}
		} catch (NullPointerException ex) {}
	}
	
	@EventHandler
	public void onInt(PlayerInteractEvent e) {
		try {
		if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cGameMaster")) {
			e.getPlayer().performCommand("gamemaster");
		}
	} catch (NullPointerException ex) {}
	}

}
