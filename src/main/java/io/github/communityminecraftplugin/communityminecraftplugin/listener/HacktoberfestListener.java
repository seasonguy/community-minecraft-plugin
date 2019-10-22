package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import java.time.YearMonth;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HacktoberfestListener implements Listener {
    /**
     * If its october ask the user if they have participated in Hacktoberfest
     * and let them know how many days there are left to participate
     */
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Calendar.getInstance().get(Calendar.MONTH) == Calendar.OCTOBER) {
            String msg = ChatColor.GOLD + "Have you participated in Hacktoberfest yet? There are still " + ChatColor.GREEN + "%s" + ChatColor.GOLD + " days left!";
            String url = ChatColor.AQUA + "https://hacktoberfest.digitalocean.com/";
            YearMonth yearMonthObject = YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1);
            int daysRemaining = yearMonthObject.lengthOfMonth() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            event.getPlayer().sendMessage(String.format(msg, String.valueOf(daysRemaining)));
            event.getPlayer().sendMessage(url);
        }
    }
}
