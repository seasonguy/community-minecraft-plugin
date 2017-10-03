package io.github.communityminecraftplugin.communityminecraftplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelloWorldCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] arg3) {
		if(c.getName().equalsIgnoreCase("helloworld")) {
			s.sendMessage(Core.HELLO_WORLD);
			return true;
		}
		return true;
	}
	
}
