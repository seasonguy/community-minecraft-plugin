package com.gmail.seasonguy445.helloworldplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.gmail.seasonguy445.helloworldplugin.Core.HELLO_WORLD;

public class HelloWorldCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] arg3) {
		
		s.sendMessage(HELLO_WORLD);
		
		return true;
	}
	
}
