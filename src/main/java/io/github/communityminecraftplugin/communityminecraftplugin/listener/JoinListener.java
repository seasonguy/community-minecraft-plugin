package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import io.github.communityminecraftplugin.communityminecraftplugin.Core;
import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Jed
 * @see <a href="https://github.com/s3xi">https://github.com/s3xi</a>
 */
public class JoinListener implements Listener {

    private Core plugin;

    public JoinListener(Core plugin) {
        this.plugin = plugin;
    }

    /**
     * Send the joining {@link Player} a welcome message defined
     * in {@link Settings} 0.5 seconds after they join.
     *
     * @param event
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(
                Settings.MESSAGES_WELCOME.getString().replace("%player", player.getDisplayName())
        );
        event.setJoinMessage(null);
    }

}
