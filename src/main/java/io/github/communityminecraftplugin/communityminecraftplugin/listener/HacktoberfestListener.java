package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.github.communityminecraftplugin.communityminecraftplugin.command.HacktoberfestCommand;

public class HacktoberfestListener implements Listener {
    /**
     * If its october ask the user if they have participated in Hacktoberfest
     * and let them know how many days there are left to participate
     */
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        HacktoberfestCommand.performHacktoberfestMessage(event.getPlayer());
    }
}
