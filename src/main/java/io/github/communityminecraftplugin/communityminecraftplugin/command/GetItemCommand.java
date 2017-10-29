package io.github.communityminecraftplugin.communityminecraftplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItem;
import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItemManager;

/**
 * 
 * @author xADudex
 * @date 29. okt. 2017
 * @see <a href="https://github.com/xGamingDudex">https://github.com/xGamingDudex</a>
 *
 */
public class GetItemCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String go = ChatColor.GOLD + "";
		String gr = ChatColor.GRAY + "";
		String pref = ChatColor.BLUE + "Items> " + gr;
		
		if(args.length == 0)
		{
			sender.sendMessage(pref + "----------- Avalible Items: -----------");
			for(UsableItem item : UsableItemManager.getInstance().getItems())
			{
				sender.sendMessage(pref + " - " + go + item.getClass().getName());
			}
			sender.sendMessage(pref);
			sender.sendMessage("Use " + go + "/" + cmd.getLabel() + " <ItemName> [player]");
		}
		else
		{
			Player target = null;
			if(sender instanceof Player)
			{
				target = (Player) sender;
			}
			if(args.length >= 2)
			{
				target = Bukkit.getPlayer(args[2]);
				if(target == null)
				{
					sender.sendMessage(pref + "Unknown player " + go + args[2]);
					return false;
				}
			}
			if(target == null)
			{
				sender.sendMessage(pref + "Please specify a player to give the item to.");
				return false;
			}
			
			String search = args[1].toLowerCase();
			for(UsableItem item : UsableItemManager.getInstance().getItems())
			{
				if(item.getClass().getName().toLowerCase().startsWith(search))
				{
					ItemStack stack = item.getExampleItem();
					target.getInventory().addItem(stack);
					sender.sendMessage(pref + "Gave one " + go + item.getClass().getName() + gr + " to " + go + target.getName());
					return false;
				}
			}
			sender.sendMessage(pref + "Unknown item " + go + args[1]);
		}
		
		return false;
	}

}
