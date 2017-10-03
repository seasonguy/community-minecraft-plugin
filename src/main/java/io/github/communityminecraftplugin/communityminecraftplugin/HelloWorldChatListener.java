package com.gmail.seasonguy445.helloworldplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.gmail.seasonguy445.helloworldplugin.Core.HELLO_WORLD;

public class HelloWorldChatListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent e){
		
		if(e.getMessage().equalsIgnoreCase(HELLO_WORLD)){
			
			e.getPlayer().sendMessage(HELLO_WORLD);
			
		}
		
	}
	
}
