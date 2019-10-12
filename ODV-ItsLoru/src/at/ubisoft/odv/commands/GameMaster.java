package at.ubisoft.odv.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameMaster implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (p.hasPermission("ubi.admin")) {
			
			Inventory inv = Bukkit.createInventory(null, 3*9, "§eGameMaster");
			inv.setItem(12, getItem(Material.CLAY, "§cCleare Wetter"));
			inv.setItem(14, getItem(Material.DOUBLE_PLANT, "§cSetze Tag"));
			p.openInventory(inv);
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
