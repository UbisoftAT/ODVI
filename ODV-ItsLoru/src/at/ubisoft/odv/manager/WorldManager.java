package at.ubisoft.odv.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import at.ubisoft.odv.gamemanager.PlayerManager;
import at.ubisoft.odv.main.Core;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class WorldManager {

	int current = 1;
	int countdown;
	public static int task;
	public static int task1;

	public WorldManager() {
	}

	public void teleport() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (new PlayerManager(all).isInGame()) {
				all.teleport(LocationManager.getConfigLocation("Spawn." + current, LocationManager.cfg));
			}
			current++;
		}
	}

	public void setDifficulty(Difficulty d) {
		for (World w : Bukkit.getWorlds()) {
			w.setDifficulty(d);
		}
	}

	public void clearWeather() {
		for (World w : Bukkit.getWorlds()) {
			w.setThundering(false);
			w.setStorm(false);
		}
	}

	public void setDay() {
		for (World w : Bukkit.getWorlds()) {
			w.setTime(1000);
		}
	}

	@SuppressWarnings("deprecation")
	public void startWorldBoarder() {

		countdown = 91;

		task = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Core.instance, () -> {

			if (Bukkit.getScheduler().isCurrentlyRunning(task)) {
				return;
			}

			countdown--;

			if (countdown == 0) {
				stop();
			}
	
		    if (countdown == 30) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 1500 200");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cDer Border wird verkleinert.", "");
				}
			} else if (countdown == 25) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 1000 200");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cDer Border wird verkleinert.", "");
				}
			} else if (countdown == 20) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 500 200");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cDer Border wird verkleinert.", "");
				}
			} else if (countdown == 15) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 100 200");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cDer Border wird verkleinert.", "");
				}
			} else if (countdown == 10) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 50 200");
				for (Player all : Bukkit.getOnlinePlayers()) {
					sendTitle(all, 20, 20, 20, "§cDer Border wird verkleinert.", "");
				}
			} else if (countdown == 5) 
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "worldborder set 25 200");
			for (Player all : Bukkit.getOnlinePlayers()) {
				sendTitle(all, 20, 20, 20, "§cDer Border wird verkleinert.", "");
			}
		}, 20 * 60, 20 * 60);
	}

	public void stop() {
		Bukkit.getScheduler().cancelTask(task);
	}

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title,
			String subtitle) {
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

		PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null,
				fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
		connection.sendPacket(packetPlayOutTimes);
		if (subtitle != null) {
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(
					PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
			connection.sendPacket(packetPlayOutSubTitle);
		}
		if (title != null) {
			title = ChatColor.translateAlternateColorCodes('&', title);
			IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
			PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE,
					titleMain);
			connection.sendPacket(packetPlayOutTitle);
		}
	}

}
