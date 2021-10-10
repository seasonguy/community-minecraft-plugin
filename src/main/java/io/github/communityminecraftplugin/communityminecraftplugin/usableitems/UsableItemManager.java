package io.github.communityminecraftplugin.communityminecraftplugin.usableitems;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.ImmutableSet;

import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItem.EventAction;
import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items.CreeperItem;
import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items.LeapItem;
import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items.PumpkinItem;
import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items.WavyStick;

/**
 * 
 * @author xADudex
 * @date 29. okt. 2017
 * @see <a href="https://github.com/xGamingDudex">https://github.com/xGamingDudex</a>
 *
 */
public class UsableItemManager implements Listener
{
	private static UsableItemManager inst;
	
	private Set<UsableItem> items = new HashSet<>();

	public UsableItemManager(JavaPlugin plugin)
	{
		if(inst != null)
		{
			throw new RuntimeException("There should only be one UsableItemManager running at a time!!!");
		}
		
		inst = this;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		registerItems(plugin);
	}
	
	/**
	 * Add an instance of items here for registration
	 */
	private void registerItems(JavaPlugin plugin)
	{
		new LeapItem();
		new CreeperItem(plugin);
		new PumpkinItem(plugin);
		new WavyStick(plugin);
	}
	
	/**
	 * Register an item. UsableItems needs to be registered here before they are active.
	 * @param item Item to register
	 * @return Returns true if added successfully. Returns false if the item was already registered.
	 */
	public boolean registerItem(UsableItem item)
	{
		return items.add(item);
	}
	
	/**
	 * Unregister an item.
	 * @param item Item to unregister
	 * @return Returns true if successfully unregistered. Returns false if item was not registered.
	 */
	public boolean unregisterItem(UsableItem item)
	{
		return items.remove(item);
	}
	
	/**
	 * 
	 * @return Returns a list of registered UsableItems
	 */
	public Set<UsableItem> getItems()
	{
		return ImmutableSet.copyOf(items);
	}

    /**
     * Search for an UsableItem based on its name.
     * @param name Item name to be found the fitting UsableItem
     * @return Optional of the found UsableItem. May be empty.
     */
	public Optional<UsableItem> searchItem(String name){
		return this.items.stream().filter(item -> item.getClass().getSimpleName().equalsIgnoreCase(name)).findAny();
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.PHYSICAL)
		{
			return;
		}
		
		Player player = event.getPlayer();
		ItemStack stack = event.getItem();
		Block block = event.getClickedBlock();
		boolean right = event.getAction().name().contains("RIGHT");
		
		callClick(player, stack, block, null, right, event);
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		PlayerInventory inventory = player.getInventory();
		ItemStack stack = event.getHand() == EquipmentSlot.HAND ? inventory.getItemInMainHand() : inventory.getItemInOffHand();
		Entity entity = event.getRightClicked();
		
		callClick(player, stack, null, entity, true, event);
	}
	
	@EventHandler
	public void onEntityDamageby(EntityDamageByEntityEvent event)
	{
		if(!(event.getDamager() instanceof Player))
		{
			return;
		}
		//TODO: Improve item detection for active item clicked
		Player player = (Player) event.getDamager();
		ItemStack item = player.getInventory().getItemInMainHand();
		Entity clicked = event.getEntity();
		
		callClick(player, item, null, clicked, false, event);
	}
	
	private void callClick(Player player, ItemStack stack, Block block, Entity entity, boolean rightClick, Cancellable event)
	{
		for(UsableItem item : items)
		{
			if(item.match(stack))
			{
				EventAction result = item.click(player, stack, block, null, rightClick);
				if(result == EventAction.CANCEL)
				{
					event.setCancelled(true);
				}
				else
				{
					event.setCancelled(false);
				}
			}
		}
	}
	
	/**
	 * Get the singleton instance of this UsableItemManager.
	 */
	public static UsableItemManager getInstance()
	{
		return inst;
	}
}
