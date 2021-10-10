package io.github.communityminecraftplugin.communityminecraftplugin.configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;

public enum Settings {

	/**
	 * Welcome message.
	 * @return {@link String}
	 */
	MESSAGES_WELCOME ("messages.welcome", "&7Welcome &a%player&7! Enjoy this plugin coded during &f#Hacktoberfest&7!"),
	HACKTOBERFEST_WELCOME("hacktoberfest.welcome", "&6Have you participated in Hacktoberfest yet? There are still &a%s&6 days left!"),
	HACKTOBERFEST_ERROR("hacktoberfest.error", "&cHacktoberfest isn't here yet!"),
	CMD_CONSOLE_ERROR("cmd.console.error", "&4You can't execute this from the console"),
	CMD_PLAYER_NOT_ONLINE("cmd.player.notonline", "&4Sorry, %player doesn't seem to be online!"),
	CMD_NO_PERMISSION("cmd.nopermission", "&cYou do not have permission!"),
	CMD_FEED_SUCCESS("cmd.feed.success", "&cYou have been fed!"),
	CMD_FLY_TOGGLE_ON("cmd.fly.toggleon", "&cYou are now able to fly!"),
	CMD_FLY_TOGGLE_OFF("cmd.fly.toggleoff","&cYou can no longer fly!"),
	CMD_FLY_OTHER_TOGGLE_ON("cmd.fly.other.toggleon", "&a%player can now fly!"),
	CMD_FLY_OTHER_TOGGLE_OFF("cmd.fly.other.toggleoff", "&c%player can no longer fly!"),
	CMD_HEAL_SUCCESS("cmd.heal.success", "&cYou have been healed!"),
	CMD_GETITEM_SUCCESS("cmd.getitem.success", "&aGave one &6%s&a to &6%player"),
	CMD_GETITEM_UNKNOWN("cmd.getitem.unknown", "&cUnknown item %s"),
	HELLO_WORLD("msg.helloworld", "Hello, World!"),
	GENERAL_PLAYER_DAMAGE ("general.player_damage", true);
	
	/**
	 * Quicker access to all the values stored in the configuration.
	 */
	private static HashMap<Settings, Object> values = new HashMap<>();
	
	private String configPath;
	private Object defaultValue;
	
	Settings(String configPath, Object defaultValue) {
		this.configPath = configPath;
		this.defaultValue = defaultValue;
	}
	
	/**
	 * Gets the default value.
	 * @return
	 */
	public Object getDefault() {
		return defaultValue;
	}
	
	/**
	 * Gets the configuration value as an {@link Object}.
	 * @return
	 */
	public Object get() {
		if (values.containsKey(this)) {
			return values.get(this);
		}
		return defaultValue;
	}
	
	/**
	 * Gets the configuration value as an {@link Integer}.
	 * @return
	 */
	public int getInt() {
		if (values.containsKey(this)) {
			return Integer.parseInt(String.valueOf(values.get(this)));
		}
		return (int) defaultValue;
	}
	
	/**
	 * Gets the configuration value as a {@link Double}.
	 * @return
	 */
	public double getDouble() {
		if (values.containsKey(this)) {
			return new BigDecimal(Double.parseDouble(String.valueOf(values.get(this)))).setScale(3, RoundingMode.HALF_UP).doubleValue();
		}
		return (double) defaultValue;
	}
	
	/**
	 * Gets the configuration value as a {@link Long}.
	 * @return
	 */
	public long getLong() {
		if (values.containsKey(this)) {
			return Long.parseLong(String.valueOf(values.get(this)));
		}
		return (long) defaultValue;
	}
	
	/**
	 * Gets the configuration value as a {@link Boolean}.
	 * @return
	 */
	public boolean getBoolean() {
		if (values.containsKey(this)) {
			return Boolean.parseBoolean(String.valueOf(values.get(this)));
		}
		return (boolean) defaultValue;
	}
	
	/**
	 * Gets the configuration value as a {@link String}.
	 * @return
	 */
	public String getString() {
		if (values.containsKey(this)) {
			return ChatColor.translateAlternateColorCodes('&', String.valueOf(values.get(this)));
		}
		return ChatColor.translateAlternateColorCodes('&', (String) defaultValue);
	}
	
	/**
	 * Gets the configuration as a {@code List<String>}.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getStringList() {
		if (values.containsKey(this)) {
			return new ArrayList<>((List<String>) values.get(this));
		}
		return new ArrayList<>((List<String>) defaultValue);
	}
	
	/**
	 * Registers settings from the specified configuration file.
	 * Clears all registered settings before registering new ones.
	 * If the configuration does not contain the setting, it will be added.
	 * @param config
	 */
	public static void registerFromFile(Configuration config) {
		values.clear();
		for (Settings s : values()) {
			//Configuration does not contain the setting value.
			if (!config.get().contains(s.configPath)) {
				//Add the setting value to the configuration and then to the HashMap.
				config.get().addDefault(s.configPath, s.defaultValue);
				values.put(s, s.defaultValue);
			} else {
				//Add the setting value to the HashMap as displayed in the configuration.
				values.put(s, config.get().get(s.configPath));
			}
		}
		//Save any changes made to the configuration.
		config.save();
	}	
}
