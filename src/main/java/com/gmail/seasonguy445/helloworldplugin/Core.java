package com.gmail.seasonguy445.helloworldplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	
	public final static String HELLO_WORLD = "Hello, world!";
	
	@Override
	public void onEnable(){
		
		getLogger().info(HELLO_WORLD);
		getCommand("helloworld").setExecutor(new HelloWorldCommand());
		getServer().getPluginManager().registerEvents(new HelloWorldChatListener(), this);
		
	}
	
	@Override
	public void onDisable(){
		
		getLogger().info(HELLO_WORLD.replace("Hello,", "Goodbye,"));
		
	}
	
	
}
