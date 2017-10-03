package io.github.communityminecraftplugin.communityminecraftplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class HelloWorldChatListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent e){
		
		if(e.getMessage().equalsIgnoreCase(Core.HELLO_WORLD)){
			
			e.getPlayer().sendMessage(Core.HELLO_WORLD);
			
		}
		
	}
	
}
