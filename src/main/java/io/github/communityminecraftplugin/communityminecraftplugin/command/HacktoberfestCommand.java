package io.github.communityminecraftplugin.communityminecraftplugin.command;

import java.time.YearMonth;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;

public class HacktoberfestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		performHacktoberfestMessage(sender);
		
		sender.sendMessage("Check out this plugin's public repository: https://github.com/seasonguy/community-minecraft-plugin");
		
		return true;
	}
	
	public static void performHacktoberfestMessage(CommandSender p) {
		if (Calendar.getInstance().get(Calendar.MONTH) == Calendar.OCTOBER) {
            String msg = Settings.HACKTOBERFEST_WELCOME.getString();
            String url = ChatColor.AQUA + "https://hacktoberfest.digitalocean.com/";
            YearMonth yearMonthObject = YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1);
            int daysRemaining = yearMonthObject.lengthOfMonth() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            p.sendMessage(String.format(msg, String.valueOf(daysRemaining)));
            p.sendMessage(url);
        } else {
        	p.sendMessage("It's not Hacktoberfest season yet!");
        }
	}
	
}
