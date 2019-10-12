package at.ubisoft.odv.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import at.ubisoft.odv.gamemanager.GameState;
import at.ubisoft.odv.gamemanager.GameType;
import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.main.Core;
import at.ubisoft.odv.manager.ScoreboardMG;
import at.ubisoft.odv.manager.UUIDFetcher;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutCustomPayload;

public class ODVProtection implements Listener {

	public static ArrayList<String> leaved = new ArrayList<String>();

	int countdown;
	public static int task;

	@EventHandler
	public void onCraft(CraftItemEvent e) {
		if (new GameType().isEnabled("bowless")) {
			if (e.getRecipe().getResult().getType() == Material.BOW) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onCraft2(CraftItemEvent e) {
		if (new GameType().isEnabled("rodless")) {
			if (e.getRecipe().getResult().getType() == Material.FISHING_ROD) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onTame(EntityTameEvent e) {
		if (new GameType().isEnabled("horseless")) {
			if (e.getEntityType() == EntityType.HORSE) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBreak2(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			e.setCancelled(false);
			return;
		}

		if (Core.gs == GameState.LOBBY && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
		}
		if (new GameType().isEnabled("vanillap")) {
			if (e.getBlock().getType() == Material.LEAVES) {
				if (Math.random() < 0.01) {
					e.getBlock().getDrops().add(getItem(Material.APPLE));
				}
			} else if (e.getBlock().getType() == Material.GRAVEL) {
				if (Math.random() < 0.01) {
					e.getBlock().getDrops().add(getItem(Material.FLINT));
				}
			}
		} else if (new GameType().isEnabled("diamondless")) {
			if (e.getBlock().getType() == Material.DIAMOND_ORE) {
				e.setCancelled(true);
			}
		} else if (new GameType().isEnabled("ironless")) {
			if (e.getBlock().getType() == Material.IRON_ORE) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onMove1(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		WorldBorder b = Bukkit.getWorld("world").getWorldBorder();
		if (!isOutsideBorder(p.getLocation())) {
			double x = b.getSize() / 2-100;
			double z = b.getSize() / 2-100;
			p.sendMessage("Nein");
			p.teleport(b.getCenter().subtract(x, 0, z));

		}
	}

	private boolean isOutsideBorder(Location location){
	    WorldBorder worldBorder = location.getWorld().getWorldBorder();

	    // These are the x and z coords of the location we want to test
	    int x = location.getBlockX();
	    int z = location.getBlockZ();

	    // This is the maximum x/z distance from the center for a player to be inside the world border
	    double size = Math.floor(worldBorder.getSize() / 2.0);

	    Location center = worldBorder.getCenter();

	    // These are the coordinates of the center
	    int cX = center.getBlockX();
	    int cZ = center.getBlockZ();

	    // What the f*ck are you trying to do here? You're adding the player's x and z values to the center location's x and z values.
	    int xAbs = Math.abs(x + cX);
	    int zAbs = Math.abs(z + cZ);

	    // I think you meant subtracting the player's x and z values from the center's x and z values to get the delta.
	    return (xAbs >= size || zAbs >= size);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (Core.gs == GameState.LOBBY) {
			e.setJoinMessage("");

			ScoreboardMG.setLobbyScoreboard(p);

			p.performCommand("warp Lobby");
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setGameMode(GameMode.SURVIVAL);
			p.sendTitle("§eOneDayVaro", "§apräsentiert euch ItsLoru");
			new PlayerManager(p).setName("§7" + UUIDFetcher.getName(p.getUniqueId()));
			if (!p.hasPermission("ubi.admin")) {
				new PlayerManager(p).addToGame();
			}
			new PlayerManager(p).clear();
			p.getInventory().setItem(0, getItem(Material.BEACON, "§cOneDayVaro"));
		} else {
			if (leaved.contains(p.getName())) {
				new PlayerManager(p).clear();
				if (!new PlayerManager(p).isInGame() && !p.hasPermission("ubi.admin")) {
					new PlayerManager(p).addToGame();
				}
				p.setGameMode(GameMode.ADVENTURE);
				p.setHealth(20);
				p.sendTitle("§eMinecraft ODV", "");
				ScoreboardMG.setScoreboard(p);
			}
		}

	}

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		if (Core.gs == GameState.LOBBY && Bukkit.hasWhitelist() && !e.getPlayer().hasPermission("ubi.admin")) {
			e.disallow(Result.KICK_WHITELIST,
					"§cDu darfst den Server erst ab §e" + Core.allowedjoin1 + ":" + Core.allowedjoin2 + " §cbetreten.");
		}
		if (Core.gs == GameState.INGAME || Core.gs == GameState.END
				|| (Core.gs == GameState.PVPLESS && !leaved.contains(e.getPlayer().getName()))
						&& Core.gs != GameState.LOBBY) {
			e.disallow(Result.KICK_OTHER, "Das Spiel hat bereits gestartet.");
		}
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		if (Core.gs == GameState.LOBBY || Core.gs == GameState.PRE_GAME || Core.gs == GameState.END
				|| e.getEntity().getGameMode() == GameMode.ADVENTURE) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage("");
		if (Core.gs == GameState.INGAME || Core.gs == GameState.PRE_GAME) {
			new PlayerManager(e.getPlayer()).removeFromGame();
			if (new PlayerManager(null).getInGame().size() < 2) {
				for (Player p1 : new PlayerManager(null).getInGame()) {
					Bukkit.broadcastMessage("§eItsLoru §8| §e" + p1.getName() + " §ahat das Spiel gewonnen.");
				}
			}
		} else if (Core.gs == GameState.PVPLESS) {
			leaved.add(e.getPlayer().getName());
		}
	}

	@EventHandler
	public void onDeath2(EntityDeathEvent e) {
		if (new GameType().isEnabled("cutclean")) {
			if (e.getEntityType() == EntityType.COW) {
				e.getDrops().add(getItem(Material.LEATHER));
			}
		}
	}

	@EventHandler
	public void onDamage2(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (Core.gs == GameState.LOBBY || Core.gs == GameState.PRE_GAME || p.getGameMode() == GameMode.ADVENTURE) {
				e.setCancelled(true);
			}
		} else {
			if (Core.gs == GameState.LOBBY || Core.gs == GameState.PRE_GAME || Core.gs == GameState.END) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (e.getPlayer().hasPermission("ubi.gamemaster")) {
			e.setFormat("§4" + e.getPlayer().getName() + " §8| §7" + e.getMessage().replaceAll("&", "§"));
		} else {
			e.setFormat("§7" + e.getPlayer().getName() + " §8| §7" + e.getMessage().replaceAll("&", "§"));
		}
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();

			if (Core.gs == GameState.LOBBY || Core.gs == GameState.PRE_GAME || Core.gs == GameState.PVPLESS
					|| p.getGameMode() == GameMode.ADVENTURE && e.getEntity() instanceof Player) {
				e.setCancelled(true);
			} else {

				if (new GameType().isEnabled("fireless")) {
					if (e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK
							&& p.getWorld().getName().equalsIgnoreCase("world")) {
						e.setCancelled(true);
					}
				}

				for (Player all : Bukkit.getOnlinePlayers()) {
					if (all.hasPermission("ubi.admin")) {
						if (e.getCause() == DamageCause.ENTITY_ATTACK) {
							all.sendMessage("§eItsLoru §8| §e" + p.getName() + " §awird von §c"
									+ e.getDamager().getName() + " §aangegriffen.");
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE && !new PlayerManager(e.getPlayer()).isInGame()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVerlassen")) {
				e.setCancelled(true);
			} else if (Core.gs == GameState.LOBBY) {
				e.setCancelled(true);
			}
		} catch (NullPointerException ex) {
		}
	}

	@EventHandler
	public void onChange(WeatherChangeEvent e) {
		if (Core.gs == GameState.LOBBY || Core.gs == GameState.PRE_GAME || Core.gs == GameState.END) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onInt(PlayerInteractEvent e) {
		try {
			if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cVerlassen")) {
				e.getPlayer().kickPlayer("§cVerlassen");
			} else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§cOneDayVaro")) {
				ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta im = (BookMeta) is.getItemMeta();
				im.addPage("");
				im.setPage(1,
						"§8» §6§lONE§e§lDAY§b§lVARO §8▎   \n\n §cRegeln §8» §7Keine Clientmodifikationen, die einen spielerischen Vorteil bieten. \n\n§7Keine Beleidigungen, sexuelle oder rassistische Inhalte in den Chat schreiben.");
				is.setItemMeta(im);
				openBook(is, e.getPlayer());
			} else if (e.getPlayer().getItemInHand().getType() == Material.FLINT_AND_STEEL) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.LAVA_BUCKET) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.FIREBALL) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.DROPPER) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.DISPENSER) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.BOW) {
				if (new GameType().isEnabled("bowless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.FISHING_ROD) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.SNOW_BALL) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			} else if (e.getPlayer().getItemInHand().getType() == Material.EGG) {
				if (new GameType().isEnabled("fireless")) {
					e.setCancelled(true);
				}
			}
		} catch (NullPointerException ex) {
		}
	}

	@EventHandler
	public void onInt1(PlayerInteractEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.ADVENTURE
				&& e.getPlayer().getItemInHand().getType() != Material.MAGMA_CREAM) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		if (Core.gs == GameState.INGAME || Core.gs == GameState.PVPLESS) {
			Location loc = p.getLocation();
			p.spigot().respawn();
			e.setDeathMessage("");

			if (!new PlayerManager(p).isInGame()) {
				e.setDeathMessage("");
				return;
			}

			if (new GameType().isEnabled("diamondless")) {
				loc.getWorld().dropItemNaturally(loc, getItem(Material.DIAMOND));
			}

			if (new GameType().isEnabled("timebomb")) {
				loc.getBlock().setType(Material.CHEST);
				ArmorStand as1 = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
				as1.setGravity(false);
				as1.setCanPickupItems(false);
				as1.setCustomName("§e§ktt§r §6§l30  §e§ktt");
				holoCounter(as1);
				as1.setCustomNameVisible(true);
				as1.setVisible(false);
				Chest chest = (Chest) loc.getBlock().getState();

				List<ItemStack> toAdd = new ArrayList<ItemStack>();

				for (int i = 0; i < 27; i++) {
					try {
						chest.getBlockInventory().addItem(new ItemStack[] { (ItemStack) e.getDrops().get(i) });
						toAdd.add(e.getDrops().get(i));
					} catch (IndexOutOfBoundsException ex) {
					}
				}

				for (ItemStack stack : toAdd) {
					e.getDrops().remove(stack);
				}

				List<ItemStack> list = e.getDrops();
				Iterator<ItemStack> i = list.iterator();
				while (i.hasNext()) {
					@SuppressWarnings("unused")
					ItemStack item = i.next();
					i.remove();
				}

				p.getInventory().clear();

				Bukkit.getScheduler().runTaskLater(Core.instance, new Runnable() {

					@Override
					public void run() {
						Bukkit.broadcastMessage(
								"§eItsLoru §8| §aDie Leiche von §e" + p.getName() + " §awurde gesprengt.");
						loc.getBlock().setType(Material.AIR);
						loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 6, false);
					}
				}, 20 * 31L);
			}

			if (p.getKiller() instanceof Player) {
				Bukkit.broadcastMessage("§eItsLoru §8| §e" + p.getName() + " §awurde von §e" + p.getKiller().getName()
						+ " §aerledigt.");
				new PlayerManager(p).spec();
			} else {
				Bukkit.broadcastMessage("§eItsLoru §8| §e" + p.getName() + " §aist gestorben.");
				new PlayerManager(p).spec();
			}

			new PlayerManager(p).removeFromGame();
			for (Player all : Bukkit.getOnlinePlayers()) {
				ScoreboardMG.setScoreboard(all);
			}
			if (new PlayerManager(p).getInGame().size() < 2) {
				for (Player p1 : new PlayerManager(p).getInGame()) {
					Bukkit.broadcastMessage("§eItsLoru §8| §e" + p1.getName() + " §ahat das Spiel gewonnen.");
				}
				Core.gs = GameState.END;
			}
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
			e.setCancelled(false);
			return;
		}
		if (Core.gs == GameState.LOBBY) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (Core.gs == GameState.LOBBY) {
			e.setCancelled(true);
		} else {
			if (new GameType().isEnabled("cutclean")) {
				if (e.getBlock().getDrops().contains(getItem(Material.IRON_ORE))) {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					p.getInventory().addItem(getItem(Material.IRON_INGOT));
				} else if (e.getBlock().getDrops().contains(getItem(Material.GOLD_ORE))) {
					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					p.getInventory().addItem(getItem(Material.GOLD_INGOT));
				}
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		if (Core.gs == GameState.INGAME) {
			if (!new PlayerManager(p).isInGame()) {
				for (Entity ent : p.getNearbyEntities(2, 2, 2)) {
					if (ent instanceof Player) {
						Vector center = p.getLocation().toVector();
						Vector toThrow = p.getLocation().toVector();

						double x = toThrow.getX() - center.getX();
						double z = toThrow.getZ() - center.getZ();

						Vector v = new Vector(x, 1, z).normalize().multiply(1);

						p.setVelocity(v);
					}
				}
			}

		}
	}

	public void holoCounter(ArmorStand m) {

		countdown = 31;

		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.instance, () -> {

			if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
				return;
			}

			countdown--;

			if (countdown == 0) {
				Bukkit.getScheduler().cancelTask(task);
				m.remove();
			}

			if (countdown < 31) {
				m.setCustomName("§e§ktt§r §6§l" + countdown + "§e§ktt");
			}
		}, 20, 20);
	}

	public void openBook(ItemStack book, Player p) {
		int slot = p.getInventory().getHeldItemSlot();
		ItemStack old = p.getInventory().getItem(slot);
		p.getInventory().setItem(slot, book);

		ByteBuf buf = Unpooled.buffer(256);
		buf.setByte(0, (byte) 0);
		buf.writerIndex(1);

		PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
		p.getInventory().setItem(slot, old);
	}

	public ItemStack getItem(Material mat) {
		return new ItemStack(mat);
	}

	public ItemStack getItem(Material mat, String displayname) {
		ItemStack is = new ItemStack(mat);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(displayname);
		is.setItemMeta(im);
		return is;
	}

}
