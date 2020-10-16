package io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItem;

/**
 * 
 * @author xADudex
 * @date 29. okt. 2017
 * @see <a href="https://github.com/xGamingDudex">https://github.com/xGamingDudex</a>
 *
 * Adds a Leap item
 */
public class LeapItem extends UsableItem
{
	
	private Map<UUID, Long> cooldown = new HashMap<>();
	
	private static final long COOLDOWN_MS = 2 * 1000;
	
	public LeapItem()
	{
		super(Material.FEATHER, ChatColor.GREEN + "Leap");
		this.register();
	}
	
	@Override
	public EventAction click(Player player, ItemStack item, Block block, Entity entity, boolean rightClick)
	{
		long nextUse = cooldown.getOrDefault(player.getUniqueId(), 0L);
		if(nextUse > System.currentTimeMillis())
		{
			double diff = (nextUse - System.currentTimeMillis()) / 1000.0; 
			DecimalFormat format = new DecimalFormat("0.00");
			player.sendMessage(ChatColor.GREEN + "Leap> " + ChatColor.GRAY +
					"Please wait another " + ChatColor.GOLD + format.format(diff) + "s" + ChatColor.GRAY + " before using this item again.");
			return EventAction.CANCEL;
		}
		
		cooldown.put(player.getUniqueId(), System.currentTimeMillis() + COOLDOWN_MS);
		
		Vector v = player.getLocation().getDirection().normalize().add(new Vector(0, 1, 0));
		
		player.setVelocity(v);
		
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
		
		return EventAction.CANCEL;
	}

}
