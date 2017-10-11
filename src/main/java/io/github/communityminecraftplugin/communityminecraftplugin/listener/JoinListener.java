package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.communityminecraftplugin.communityminecraftplugin.Core;
import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;

/**
 * 
 * @author Jed
 * @see <a href="https://github.com/s3xi">https://github.com/s3xi</a>
 *
 */
public class JoinListener implements Listener {

	private Core plugin;
	
	public JoinListener(Core plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Send the joining {@link Player} a welcome message defined 
	 * in {@link Settings} 0.5 seconds after they join.
	 * @param event
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (event.getPlayer() != null && event.getPlayer().isOnline()) {
					event.getPlayer().sendMessage(Settings.MESSAGES_WELCOME.getString().replaceAll("%player", event.getPlayer().getDisplayName()));
				}
			}
		}.runTaskLater(plugin, 10);
	}
}
