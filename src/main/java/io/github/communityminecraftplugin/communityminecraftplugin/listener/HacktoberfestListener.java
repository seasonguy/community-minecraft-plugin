package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import java.util.Calendar;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HacktoberfestListener implements Listener {

    @SuppressWarnings("MagicConstant")
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Calendar.getInstance().get(Calendar.MONTH) == Calendar.OCTOBER) {
            event.getPlayer().sendMessage(ChatColor.GOLD + "Have you participated in Hacktoberfest yet? There are still "
                                          + ChatColor.GREEN + (31 - Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + ChatColor.GOLD + " days left!");

        }
    }
}
