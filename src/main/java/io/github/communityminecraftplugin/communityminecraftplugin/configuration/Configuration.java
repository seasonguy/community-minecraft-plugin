package io.github.communityminecraftplugin.communityminecraftplugin.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * @author Jed
 * @see <a href="https://github.com/s3xi">https://github.com/s3xi</a>
 *
 */
public class Configuration {

	private JavaPlugin plugin;

	private File file;
	private FileConfiguration config;

	public Configuration(JavaPlugin plugin, File file) {
		this.plugin = plugin;
		this.file = new File(plugin.getDataFolder() + File.separator + file);
		create();
		this.config = YamlConfiguration.loadConfiguration(this.file);
		reload();
	}

	/**
	 * Creates the file on disk.
	 */
	private void create() {
		if (!file.getParentFile().exists()) {
			try {
				file.getParentFile().mkdirs();
			} catch (Exception e) {
				plugin.getLogger().info("Failed to generate directory!");
				e.printStackTrace();
			}
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception e) {
				plugin.getLogger().info("Failed to generate " + file.getName() + "!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the {@link FileConfiguration} instance from this {@link Configuration}.
	 * @return
	 */
	public FileConfiguration get() {
		return config;
	}
	
	/**
	 * Reloads the file on disk.
	 */
	public void reload() {
		create();
		try {
			config.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves any changes to disk.
	 */
	public void save() {
		try {
			config.options().copyDefaults(true);
			config.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes the file from disk.
	 * @return
	 */
	public boolean delete() {
		try {
			if (file.delete()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}