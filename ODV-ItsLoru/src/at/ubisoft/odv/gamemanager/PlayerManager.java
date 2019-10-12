package at.ubisoft.odv.gamemanager;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.authlib.GameProfile;

import at.ubisoft.odv.manager.LocationManager;
import at.ubisoft.odv.manager.UUIDFetcher;

public class PlayerManager {
	
	public static ArrayList<Player> list = new ArrayList<Player>();
	private Field nameField;

	Player p;
	
	public PlayerManager(Player p) {
		this.p = p;
	}
	
	public void clear() {
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		p.getActivePotionEffects().clear();
		p.setLevel(0);
	}
	
	public ArrayList<Player> getInGame() {
		return list;
	}
	
	public void addToGame() {
		list.add(p);
	}
	
	public void removeFromGame() {
		list.remove(p);
	}

	public boolean isInGame() {
		return list.contains(p);
	}
	
	public void setName(String name) {
		nameField = getField(GameProfile.class, "name");
		CraftPlayer cp = (CraftPlayer) p;
		p.setPlayerListName(name);
		p.setCustomName(name);
		try {
			nameField.set(cp.getProfile(), name);
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}

	public void spec() {
		p.sendMessage("§eItsLoru §8| §cDu schaust dem Spiel nun zu.");
		
		for (Player all : new PlayerManager(null).getInGame()) {
			all.hidePlayer(p);
		}
		
		p.teleport(LocationManager.getConfigLocation("SpecSpawn", LocationManager.cfg).add(0, 5, 0));
		
		p.spigot().setCollidesWithEntities(false);			
		
		new PlayerManager(p).clear();
		p.setGameMode(GameMode.ADVENTURE); 

		
		setName("§o§7" + UUIDFetcher.getName(p.getUniqueId()));
		
		if (p.hasPermission("ubi.admin")) {
			p.getInventory().setItem(0, getItem(Material.NETHER_STAR, "§cGameMaster"));
		}
		p.getInventory().setItem(8, getItem(Material.MAGMA_CREAM, "§cVerlassen"));
		p.setAllowFlight(true);
		p.setFlying(true);
	
	}
	
	private Field getField(Class<?> clazz, String name) {

		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (NoSuchFieldException | SecurityException e) {
			return null;
		}

	}
	
	public ItemStack getItem(Material mat, String displayname) {
		ItemStack is = new ItemStack(mat);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayname);
		is.setItemMeta(im);
		return is;
	}
	
}
