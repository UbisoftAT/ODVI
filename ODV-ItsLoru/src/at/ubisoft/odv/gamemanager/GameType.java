package at.ubisoft.odv.gamemanager;

import java.util.Date;

import at.ubisoft.odv.manager.ODVConfig;

public class GameType {

	public static boolean bowless;
	public static boolean cutclean;
	public static boolean diamondless;
	public static boolean horseless;
	public static boolean rodless;
	public static boolean timebomb;
	public static boolean vanillap;
	public static boolean fireless;
	public static boolean backpack;
	public static boolean ironless;
	public static boolean splitedlive;
	
	public GameType() {
	}

	public void enable(String type) {
		if (type.equalsIgnoreCase("bowless")) {
			bowless = true;
		} else if (type.equalsIgnoreCase("cutclean")) {
			cutclean = true;
		} else if (type.equalsIgnoreCase("diamondless")) {
			diamondless = true;
		} else if (type.equalsIgnoreCase("horseless")) {
			horseless = true;
		} else if (type.equalsIgnoreCase("rodless")) {
			rodless = true;
		} else if (type.equalsIgnoreCase("timebomb")) {
			timebomb = true;
		} else if (type.equalsIgnoreCase("vanillap")) {
			vanillap = true;
		} else if (type.equalsIgnoreCase("fireless")) {
			fireless = true;
		} else if (type.equalsIgnoreCase("backpack")) {
			backpack = true;
		} else if (type.equalsIgnoreCase("ironless")) {
			ironless = true;
		} else if (type.equalsIgnoreCase("splitedlive")) {
			splitedlive = true;
		} else {
			return;
		}
	}

	public void disable(String type) {
		if (type.equalsIgnoreCase("bowless")) {
			bowless = false;
		} else if (type.equalsIgnoreCase("cutclean")) {
			cutclean = false;
		} else if (type.equalsIgnoreCase("diamondless")) {
			diamondless = false;
		} else if (type.equalsIgnoreCase("horseless")) {
			horseless = false;
		} else if (type.equalsIgnoreCase("rodless")) {
			rodless = false;
		} else if (type.equalsIgnoreCase("timebomb")) {
			timebomb = false;
		} else if (type.equalsIgnoreCase("vanillap")) {
			vanillap = false;
		} else if (type.equalsIgnoreCase("fireless")) {
			fireless = false;
		} else if (type.equalsIgnoreCase("backpack")) {
			backpack = false;
		} else if (type.equalsIgnoreCase("ironless")) {
			ironless = false;
		} else if (type.equalsIgnoreCase("splitedlive")) {
			splitedlive = false;
		} else {
			return;
		}
	}

	public boolean isEnabled(String type) {

		if (type.equalsIgnoreCase("bowless")) {
			return bowless;
		} else if (type.equalsIgnoreCase("cutclean")) {
			return cutclean;
		} else if (type.equalsIgnoreCase("diamondless")) {
			return diamondless;
		} else if (type.equalsIgnoreCase("horseless")) {
			return horseless;
		} else if (type.equalsIgnoreCase("rodless")) {
			return rodless;
		} else if (type.equalsIgnoreCase("timebomb")) {
			return timebomb;
		} else if (type.equalsIgnoreCase("vanillap")) {
			return vanillap;
		} else if (type.equalsIgnoreCase("fireless")) {
			return fireless;
		} else if (type.equalsIgnoreCase("backpack")) {
			return backpack;
		} else if (type.equalsIgnoreCase("ironless")) {
			return ironless;
		} else if (type.equalsIgnoreCase("splitedlive")) {
			return splitedlive;
		}
		return false;
	}

	public String getDay() {
		Date date = new Date();
		@SuppressWarnings("deprecation")
		int day = date.getDay();
		
		if (day == 1)
			return "Montag";
		if (day == 2)
			return "Dienstag";
		if (day == 3)
			return "Mittwoch";
		if (day == 4)
			return "Donnerstag";
		if (day == 5)
			return "Freitag";
		if (day == 6)
			return "Samstag";
		if (day == 0)
			return "Sonntag";
		
		return "Error";
	}

	public void getDaySettings() {
		bowless = new ODVConfig().get(getDay() + ".bowless");
		cutclean = new ODVConfig().get(getDay() + ".cutclean");
		diamondless = new ODVConfig().get(getDay() + ".diamondless");
		horseless = new ODVConfig().get(getDay() + ".horseless");
		rodless = new ODVConfig().get(getDay() + ".rodless");
		timebomb = new ODVConfig().get(getDay() + ".timebomb");
		vanillap = new ODVConfig().get(getDay() + ".vanillap");
		fireless = new ODVConfig().get(getDay() + ".fireless");
		backpack = new ODVConfig().get(getDay() + ".backpack");
		ironless = new ODVConfig().get(getDay() + ".ironless");
		splitedlive = new ODVConfig().get(getDay() + ".splitedlive");
		

	}
	
	public String getPlannedTime() {
		return new ODVConfig().getString("PlannedTime");
	}

}
