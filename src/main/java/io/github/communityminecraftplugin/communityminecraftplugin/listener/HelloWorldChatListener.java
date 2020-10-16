package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;

public class HelloWorldChatListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent e){
		if(e.getMessage().equalsIgnoreCase(Settings.HELLO_WORLD.getString())){
			e.getPlayer().sendMessage(Settings.HELLO_WORLD.getString());
		}
	}
	
}
