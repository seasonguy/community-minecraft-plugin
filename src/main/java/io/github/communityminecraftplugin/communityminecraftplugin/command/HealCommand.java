package io.github.communityminecraftplugin.communityminecraftplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        //Checks if sender instance of player
        if(!(sender instanceof Player)) {
            System.out.println(Settings.CMD_CONSOLE_ERROR);
        } else {
            Player player = (Player) sender;
            //Sets health of player to 10 hearts
            player.setHealth(player.getHealth());
            player.sendMessage(Settings.CMD_HEAL_SUCCESS.getString());
        }
        return true;
    }
}
