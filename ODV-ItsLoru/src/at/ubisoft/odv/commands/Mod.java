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

import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.GameType;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.ScoreboardMG;

public class Mod implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;

		if (p.hasPermission("ubi.admin")) {
			if (args.length == 0) {
				p.sendMessage("§8§m-----------------");
				p.sendMessage("§e/mod enable [Mod]");
				p.sendMessage("§e/mod disable [Mod]");
				p.sendMessage("§e/mod gui");
				p.sendMessage("§e/mod list");
				p.sendMessage("§8§m-----------------");
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					p.sendMessage("§8§m-----------------");
					p.sendMessage("§8» §ebowless");
					p.sendMessage("§8» §ecutclean");
					p.sendMessage("§8» §ediamondless");
					p.sendMessage("§8» §ehorseless");
					p.sendMessage("§8» §erodless");
					p.sendMessage("§8» §etimebomb");
					p.sendMessage("§8» §evanillap");
					p.sendMessage("§8» §efireless");
					p.sendMessage("§8» §ebackpack");
					p.sendMessage("§8» §eironless");
					p.sendMessage("§8» §elimitations");
					p.sendMessage("§8» §esplitedlive");
					p.sendMessage("§8§m-----------------");

				}
				else if (args[0].equalsIgnoreCase("gui")) {
					Inventory inv = Bukkit.createInventory(null, 3 * 9, "§eModules");
					if (new GameType().isEnabled("bowless")) {
						inv.setItem(0, getItem(Material.INK_SACK, "§eBogenlos", 10));
					} else {
						inv.setItem(0, getItem(Material.INK_SACK, "§eBogenlos", 1));
					}
					if (new GameType().isEnabled("cutclean")) {
						inv.setItem(1, getItem(Material.INK_SACK, "§eCutClean", 10));
					} else {
						inv.setItem(1, getItem(Material.INK_SACK, "§eCutClean", 1));
					}
					if (new GameType().isEnabled("diamondless")) {
						inv.setItem(2, getItem(Material.INK_SACK, "§eDiamantenLos", 10));
					} else {
						inv.setItem(2, getItem(Material.INK_SACK, "§eDiamantenLos", 1));
					}

					if (new GameType().isEnabled("horseless")) {
						inv.setItem(3, getItem(Material.INK_SACK, "§ePferdeLos", 10));
					} else {
						inv.setItem(3, getItem(Material.INK_SACK, "§ePferdeLos", 1));
					}
					if (new GameType().isEnabled("rodless")) {
						inv.setItem(4, getItem(Material.INK_SACK, "§eRodLess", 10));
					} else {
						inv.setItem(4, getItem(Material.INK_SACK, "§eRodLess", 1));
					}

					if (new GameType().isEnabled("timebomb")) {
						inv.setItem(5, getItem(Material.INK_SACK, "§eZeitBombe", 10));
					} else {
						inv.setItem(5, getItem(Material.INK_SACK, "§eZeitBombe", 1));
					}

					if (new GameType().isEnabled("vanillap")) {
						inv.setItem(6, getItem(Material.INK_SACK, "§eVanillaPlus", 10));
					} else {
						inv.setItem(6, getItem(Material.INK_SACK, "§eVanillaPlus", 1));
					}

					if (new GameType().isEnabled("fireless")) {
						inv.setItem(7, getItem(Material.INK_SACK, "§eFeuerLos", 10));
					} else {
						inv.setItem(7, getItem(Material.INK_SACK, "§eFeuerLos", 1));
					}

					if (new GameType().isEnabled("backpack")) {
						inv.setItem(8, getItem(Material.INK_SACK, "§eBackPack", 10));
					} else {
						inv.setItem(8, getItem(Material.INK_SACK, "§eBackPack", 1));
					}

					if (new GameType().isEnabled("ironless")) {
						inv.setItem(9, getItem(Material.INK_SACK, "§eEisenLos", 10));
					} else {
						inv.setItem(9, getItem(Material.INK_SACK, "§eEisenLos", 1));
					}

					if (new GameType().isEnabled("limitations")) {
						inv.setItem(10, getItem(Material.INK_SACK, "§eLimitationen", 10));
					} else {
						inv.setItem(10, getItem(Material.INK_SACK, "§eLimitationen", 1));
					}

					if (new GameType().isEnabled("splitedlive")) {
						inv.setItem(10, getItem(Material.INK_SACK, "§eGespaltetes Leben", 10));
					} else {
						inv.setItem(10, getItem(Material.INK_SACK, "§eGespaltetes Leben", 1));
					}
					p.openInventory(inv);
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("enable")) {
					p.sendMessage("§eItsLoru §8| §aErfolgreich.");
					new GameType().enable(args[1]);
					if (Core.gs == GameState.PRE_GAME || Core.gs == GameState.INGAME || Core.gs == GameState.PVPLESS) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							ScoreboardMG.setScoreboard(all);
						}
					}
				} else if (args[0].equalsIgnoreCase("disable")) {
					p.sendMessage("§eItsLoru §8| §aErfolgreich.");
					new GameType().disable(args[1]);
					if (Core.gs == GameState.PRE_GAME || Core.gs == GameState.INGAME || Core.gs == GameState.PVPLESS) {
						for (Player all : Bukkit.getOnlinePlayers()) {
							ScoreboardMG.setScoreboard(all);
						}
					}
				}
			}
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

	public ItemStack getItem(Material mat, String displayname, int subid) {
		ItemStack is = new ItemStack(mat, 1, (short) subid);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayname);
		is.setItemMeta(im);
		return is;
	}
}
