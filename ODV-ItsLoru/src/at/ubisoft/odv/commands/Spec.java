package at.ubisoft.odv.commands;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.manager.LocationManager;
import at.ubisoft.odv.manager.UUIDFetcher;

public class Spec implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player)sender;
		
		if (p.hasPermission("ubi.admin")) {
			new PlayerManager(p).removeFromGame();
			p.sendMessage("§eItsLoru §8| §cDu schaust dem Spiel nun zu.");
			
			for (Player all : new PlayerManager(null).getInGame()) {
				all.hidePlayer(p);
			}
			
			p.teleport(LocationManager.getConfigLocation("SpecSpawn", LocationManager.cfg).add(0, 5, 0));
			
			p.spigot().setCollidesWithEntities(false);			
			
			new PlayerManager(p).clear();
			p.setGameMode(GameMode.ADVENTURE); 

			new PlayerManager(p).setName("§o§7" + UUIDFetcher.getName(p.getUniqueId()));
			
			p.getInventory().setItem(8, getItem(Material.MAGMA_CREAM, "§cVerlassen"));
			if (p.hasPermission("ubi.admin")) {
				p.getInventory().setItem(0, getItem(Material.NETHER_STAR, "§cGameMaster"));
			}
			p.setAllowFlight(true);
			p.setFlying(true);
		
		} else {
			p.sendMessage("§eItsLoru §8| §cDazu hast du keine Rechte!");
		}
		
		return false;
	}
	
	public ItemStack getItem(Material mat, String displayname) {
		ItemStack is = new ItemStack(mat);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayname);
		is.setItemMeta(im);
		return is;
	}

}
