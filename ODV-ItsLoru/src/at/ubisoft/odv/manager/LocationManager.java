package at.ubisoft.odv.manager;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {
	
  public static File file = new File("plugins/ODV/warps.yml");
  public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
  
  public static void createConfigLocation(Location loc, String path, File file, YamlConfiguration cfg) {
    cfg.set(String.valueOf(path) + ".World", loc.getWorld().getName());
    cfg.set(String.valueOf(path) + ".X", Double.valueOf(loc.getX()));
    cfg.set(String.valueOf(path) + ".Y", Double.valueOf(loc.getY()));
    cfg.set(String.valueOf(path) + ".Z", Double.valueOf(loc.getZ()));
    cfg.set(String.valueOf(path) + ".Yaw", Float.valueOf(loc.getYaw()));
    cfg.set(String.valueOf(path) + ".Pitch", Float.valueOf(loc.getPitch()));
    try {
      cfg.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static Location getConfigLocation(String path, YamlConfiguration cfg) {
    World w = Bukkit.getWorld(cfg.getString(String.valueOf(path) + ".World"));
    double x = cfg.getDouble(String.valueOf(path) + ".X");
    double y = cfg.getDouble(String.valueOf(path) + ".Y");
    double z = cfg.getDouble(String.valueOf(path) + ".Z");
    float yaw = (float)cfg.getDouble(String.valueOf(path) + ".Yaw");
    float pitch = (float)cfg.getDouble(String.valueOf(path) + ".Pitch");
    
    return new Location(w, x, y, z, yaw, pitch);
  }
}