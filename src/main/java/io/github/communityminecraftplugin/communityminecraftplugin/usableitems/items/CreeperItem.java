package io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItem;

/**
 * 
 * @author xADudex
 * @date 29. okt. 2017
 * @see <a href="https://github.com/xGamingDudex">https://github.com/xGamingDudex</a>
 *
 */
public class CreeperItem extends UsableItem implements Listener
{
	
	private static final int FLY_TICKS = 20 * 5;
	private static final PotionEffect INVISIBLE = new PotionEffect(PotionEffectType.INVISIBILITY, FLY_TICKS, 1, true, false);
	
	private Plugin plugin;

	public CreeperItem(Plugin plugin)
	{
		super(Material.STICK, ChatColor.GREEN + "Throw Creeper");
		this.plugin = plugin;
	}

	@Override
	public EventAction click(Player player, ItemStack item, Block block, Entity entity, boolean rightClick)
	{
		Bat bat = player.getWorld().spawn(player.getEyeLocation(), Bat.class);
		bat.setInvulnerable(true);
		bat.addPotionEffect(INVISIBLE);
		bat.setVelocity(player.getLocation().getDirection());
		
		Creeper creeper = player.getWorld().spawn(player.getEyeLocation(), Creeper.class);
		creeper.setInvulnerable(true);
		creeper.setAI(false);
		
		Bukkit.getScheduler().runTaskLater(plugin, () ->
		{
			bat.remove();
			creeper.remove();
			creeper.getWorld().createExplosion(creeper.getLocation(), 0);
		}, FLY_TICKS);
		
		return null;
	}

}
