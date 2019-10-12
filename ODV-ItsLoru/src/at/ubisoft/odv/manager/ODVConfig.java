package at.ubisoft.odv.manager;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;

public class ODVConfig {
	
	File file = new File("plugins/ODV/config.yml");
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public ODVConfig() {}
	
	public void create() {
		if (!file.exists()) {
			
		cfg.set("PlannedTime", "19:30");
		cfg.set("AllowedPlayerJoinTime.Stunde", "19");
		cfg.set("AllowedPlayerJoinTime.Minute", "10");
		cfg.set("StartTime.Stunde", "19");
		cfg.set("StartTime.Minute", "50");
		
		cfg.set("Montag.bowless", false);
		cfg.set("Montag.cutclean", false);
		cfg.set("Montag.diamondless", false);
		cfg.set("Montag.horseless", false);
		cfg.set("Montag.rodless", false);
		cfg.set("Montag.timebomb", false);
		cfg.set("Montag.vanillap", false);
		cfg.set("Montag.fireless", false);
		cfg.set("Montag.backpack", false);
		cfg.set("Montag.ironless", false);
		cfg.set("Montag.splitedlive", false);
		//
		cfg.set("Dienstag.bowless", false);
		cfg.set("Dienstag.cutclean", false);
		cfg.set("Dienstag.diamondless", false);
		cfg.set("Dienstag.horseless", false);
		cfg.set("Dienstag.rodless", false);
		cfg.set("Dienstag.timebomb", false);
		cfg.set("Dienstag.vanillap", false);
		cfg.set("Dienstag.fireless", false);
		cfg.set("Dienstag.backpack", false);
		cfg.set("Dienstag.ironless", false);
		cfg.set("Dienstag.splitedlive", false);
		//
		cfg.set("Mittwoch.bowless", false);
		cfg.set("Mittwoch.cutclean", false);
		cfg.set("Mittwoch.diamondless", false);
		cfg.set("Mittwoch.horseless", false);
		cfg.set("Mittwoch.rodless", false);
		cfg.set("Mittwoch.timebomb", false);
		cfg.set("Mittwoch.vanillap", false);
		cfg.set("Mittwoch.fireless", false);
		cfg.set("Mittwoch.backpack", false);
		cfg.set("Mittwoch.ironless", false);
		cfg.set("Mittwoch.splitedlive", false);
		//
		cfg.set("Donnerstag.bowless", false);
		cfg.set("Donnerstag.cutclean", false);
		cfg.set("Donnerstag.diamondless", false);
		cfg.set("Donnerstag.horseless", false);
		cfg.set("Donnerstag.rodless", false);
		cfg.set("Donnerstag.timebomb", false);
		cfg.set("Donnerstag.vanillap", false);
		cfg.set("Donnerstag.fireless", false);
		cfg.set("Donnerstag.backpack", false);
		cfg.set("Donnerstag.ironless", false);
		cfg.set("Donnerstag.splitedlive", false);
		//
		cfg.set("Freitag.bowless", false);
		cfg.set("Freitag.cutclean", false);
		cfg.set("Freitag.diamondless", false);
		cfg.set("Freitag.horseless", false);
		cfg.set("Freitag.rodless", false);
		cfg.set("Freitag.timebomb", false);
		cfg.set("Freitag.vanillap", false);
		cfg.set("Freitag.fireless", false);
		cfg.set("Freitag.backpack", false);
		cfg.set("Freitag.ironless", false);
		cfg.set("Freitag.splitedlive", false);
		//
		cfg.set("Samstag.bowless", false);
		cfg.set("Samstag.cutclean", false);
		cfg.set("Samstag.diamondless", false);
		cfg.set("Samstag.horseless", false);
		cfg.set("Samstag.rodless", false);
		cfg.set("Samstag.timebomb", false);
		cfg.set("Samstag.vanillap", false);
		cfg.set("Samstag.fireless", false);
		cfg.set("Samstag.backpack", false);
		cfg.set("Samstag.ironless", false);
		cfg.set("Samstag.splitedlive", false);
		//
		cfg.set("Sonntag.bowless", false);
		cfg.set("Sonntag.cutclean", false);
		cfg.set("Sonntag.diamondless", false);
		cfg.set("Sonntag.horseless", false);
		cfg.set("Sonntag.rodless", false);
		cfg.set("Sonntag.timebomb", false);
		cfg.set("Sonntag.vanillap", false);
		cfg.set("Sonntag.fireless", false);
		cfg.set("Sonntag.backpack", false);
		cfg.set("Sonntag.ironless", false);
		cfg.set("Sonntag.splitedlive", false);
		}
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean get(String a) {
		return (boolean) cfg.get(a);
	}
	
	public String getString(String a) {
		return (String) cfg.get(a);
	}
	
}
