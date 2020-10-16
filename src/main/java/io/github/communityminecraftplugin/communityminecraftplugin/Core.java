package io.github.communityminecraftplugin.communityminecraftplugin;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.communityminecraftplugin.communityminecraftplugin.command.FlyCommand;
import io.github.communityminecraftplugin.communityminecraftplugin.command.GetItemCommand;
import io.github.communityminecraftplugin.communityminecraftplugin.command.HacktoberfestCommand;
import io.github.communityminecraftplugin.communityminecraftplugin.command.HealCommand;
import io.github.communityminecraftplugin.communityminecraftplugin.command.HelloWorldCommand;
import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Configuration;
import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;
import io.github.communityminecraftplugin.communityminecraftplugin.listener.DamageListener;
import io.github.communityminecraftplugin.communityminecraftplugin.listener.HacktoberfestListener;
import io.github.communityminecraftplugin.communityminecraftplugin.listener.HelloWorldChatListener;
import io.github.communityminecraftplugin.communityminecraftplugin.listener.JoinListener;
import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItemManager;

public class Core extends JavaPlugin {
	
	@Override
	public void onEnable(){
		preInit();
		postInit();
	}
	
	@Override
	public void onDisable(){
		getLogger().info(Settings.HELLO_WORLD.getString().replace("Hello,", "Goodbye,"));
	}
	
	/**
	 * Is called pre-initialisation.
	 * I.e. loading configurations, etc.
	 */
	private void preInit() {
		Settings.registerFromFile(new Configuration(this, new File("settings.yml")));
	}
	
	/**
	 * Is called post-initialisation.
	 * I.e. loading objects that depend on configurations, etc.
	 */
	private void postInit() {
		getLogger().info(Settings.HELLO_WORLD.getString());

		getCommand("helloworld").setExecutor(new HelloWorldCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("getitem").setExecutor(new GetItemCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("hacktoberfest").setExecutor(new HacktoberfestCommand());

		getServer().getPluginManager().registerEvents(new HelloWorldChatListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		getServer().getPluginManager().registerEvents(new DamageListener(), this);
		getServer().getPluginManager().registerEvents(new HacktoberfestListener(), this);
		// Register the UsableItemManager
		new UsableItemManager(this);
	}
	
}
