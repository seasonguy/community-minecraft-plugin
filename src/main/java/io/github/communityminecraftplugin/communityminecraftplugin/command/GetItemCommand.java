package io.github.communityminecraftplugin.communityminecraftplugin.command;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;
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
				sender.sendMessage(pref + " - " + go + item.getClass().getSimpleName());
			}
			sender.sendMessage(pref);
			
			return false;
		}

		else {
            Player target = null;
            if (sender instanceof Player) {
                target = (Player) sender;
            }
            if (args.length >= 2) {
                target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(Settings.CMD_PLAYER_NOT_ONLINE.getString().replace("%player", args[1]));
                    return false;
                }
            }
            if (target == null) {
                sender.sendMessage(Settings.CMD_PLAYER_NOT_ONLINE.getString().replace("%player", args[1]));
                return false;
            }

            String search = args[0].toLowerCase();
            //Find Optional UsableItem
            Optional<UsableItem> optitem = UsableItemManager.getInstance().searchItem(search);

            if (optitem.isPresent()) {
                UsableItem item = optitem.get();
                ItemStack stack = item.getExampleItem();
                target.getInventory().addItem(stack);
                sender.sendMessage(String.format(Settings.CMD_GETITEM_SUCCESS.getString(), item.getName()).replace("%player", target.getName()));
                return true;

            } else {

                sender.sendMessage(String.format(Settings.CMD_GETITEM_UNKNOWN.getString(), search));
            }
        }
		
		return false;
	}

}
