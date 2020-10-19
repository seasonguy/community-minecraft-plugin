package io.github.communityminecraftplugin.communityminecraftplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;

// Author: PixeL (https://github.com/PixeLInc)
public class FlyCommand implements CommandExecutor {

    // Syntax: /fly (Optional <user>)

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(args.length != 1) { // They didn't supply a user
            if(!(commandSender instanceof Player)){ // Console, or something tried to fly
                commandSender.sendMessage(Settings.CMD_CONSOLE_ERROR.getString());
                return true;
            }

            Player user = (Player) commandSender;

            if (!user.hasPermission("community.fly")) { // We need to make sure they have permission to fly.. we can't have non-astronauts flying around!
                user.sendMessage(Settings.CMD_NO_PERMISSION.getString());
                return true;
            }

            toggleFlight(user);
            
            return true;
        }

        // They supplied a username
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) { // We can't find the specified player
            commandSender.sendMessage(Settings.CMD_PLAYER_NOT_ONLINE.getString().replace("%player", args[0]));
            return true;
        }

        if(!commandSender.hasPermission("community.fly.others")) {
            commandSender.sendMessage(Settings.CMD_NO_PERMISSION.getString());
            return true;
        }

        toggleFlight(target);
        
        commandSender.sendMessage((target.getAllowFlight() ? Settings.CMD_FLY_OTHER_TOGGLE_ON.getString() : Settings.CMD_FLY_OTHER_TOGGLE_OFF.getString()).replace("%player", target.getName()));
        return true;
    }
    
    private void toggleFlight(Player user) {
        if(user.getAllowFlight()) { // If they can already fly
            user.setAllowFlight(false);
            user.setFlying(false);
        } else{
            user.setAllowFlight(true);
            user.setFlying(false);
        }

        user.sendMessage(user.getAllowFlight() ? Settings.CMD_FLY_TOGGLE_ON.getString() : Settings.CMD_FLY_TOGGLE_OFF.getString());
    }
}
