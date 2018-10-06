package io.github.communityminecraftplugin.communityminecraftplugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        //Checks if sender instance of player
        if(!(sender instanceof Player)) {
            System.out.println(ChatColor.RED + "You can't execute this from the console");
        } else {
            Player player = (Player) sender;
            //Sets health of player to 10 hearts
            player.setHealth(20);
            System.out.println(ChatColor.GREEN + "You have been healed");
        }
        return true;
    }
}
