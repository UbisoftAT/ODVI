package at.ubisoft.odv.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import at.ubisoft.odv.gamemanager.GameType;

public class BackPack implements CommandExecutor {
	
	public static HashMap<Player, ItemStack[]> bp = new HashMap<Player, ItemStack[]>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		
		if (new GameType().isEnabled("backpack")) {
			Inventory inv = Bukkit.createInventory(null, 4*9, "§eBackPack");
			if (bp.get(p) != null) {
			inv.setContents(bp.get(p));
			}
			p.openInventory(inv);
		} else {
			p.sendMessage("§eItsLoru §8| §cDer BackPack ist derzeit deaktiviert.");
		}
		
		return false;
	}

}
