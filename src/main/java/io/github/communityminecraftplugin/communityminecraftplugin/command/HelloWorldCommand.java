package io.github.communityminecraftplugin.communityminecraftplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;

public class HelloWorldCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] arg3) {
	    s.sendMessage(Settings.HELLO_WORLD.getString());
	    return true;
	}
	
}
