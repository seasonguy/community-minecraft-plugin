package io.github.communityminecraftplugin.communityminecraftplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Author: PixeL (https://github.com/PixeLInc)
public class FlyCommand implements CommandExecutor {

    // Syntax: /fly (Optional <user>)

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(args.length != 1) { // They didn't supply a user
            if(!(commandSender instanceof Player)){ // Console, or something tried to fly
                commandSender.sendMessage("Sorry, Console isn't able to fly to the stars :(");
                return true;
            }

            Player user = (Player) commandSender;

            if (!user.hasPermission("community.fly")) { // We need to make sure they have permission to fly.. we can't have non-astronauts flying around!
                user.sendMessage(ChatColor.RED + "You do not have permission to fly!");
                return true;
            }

            if(user.getAllowFlight()) { // If they can already fly
                user.setAllowFlight(false);
                user.setFlying(false);
            } else{
                user.setAllowFlight(true);
                user.setFlying(false);
            }

            user.sendMessage(user.getAllowFlight() ? ChatColor.GREEN + "You are now able to fly!" : ChatColor.RED + "You can no longer fly!");
            return true;
        }

        // They supplied a username
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) { // We can't find the specified player
            commandSender.sendMessage(ChatColor.RED + "Sorry, " + args[0] + " doesn't seem to be online.");
            return true;
        }

        if(!commandSender.hasPermission("community.fly.others")) {
            commandSender.sendMessage(ChatColor.RED  + "You don't have permission to make others fly!");
            return true;
        }

        if(target.getAllowFlight()) {
            target.setAllowFlight(false);
            target.setFlying(false);
        } else {
            target.setAllowFlight(true);
            target.setFlying(true);
        }

        target.sendMessage(target.getAllowFlight() ? ChatColor.GREEN + "You were given the ability to fly by " + commandSender.getName() + "!" : ChatColor.RED + commandSender.getName() + " has taken away your ability to fly!");
        commandSender.sendMessage(target.getAllowFlight() ? ChatColor.GREEN + target.getName() + " can now fly!" : ChatColor.RED + target.getName() + " can no longer fly!");
        return true;
    }
}
