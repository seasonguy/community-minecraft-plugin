package io.github.communityminecraftplugin.communityminecraftplugin.usableitems;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 
 * @author xADudex
 * @date 29. okt. 2017
 * @see <a href="https://github.com/xGamingDudex">https://github.com/xGamingDudex</a>
 *
 */
public abstract class UsableItem
{
	public static enum EventAction 
	{
		NONE,CANCEL,UNCANCEL;
	}
	
	protected final Material mat;
	protected final String name;

	/**
	 * A usable item which can be used to trigger code on different events
	 * @param mat Material to look for
	 * @param name Display name on the item to look for (may be null)
	 * 
	 * To activate this item the material the player is holding must be the same as this.
	 * The display name on the item the player uses also needs to match this item exactly (unless null specified
	 * 	then any item with the same material will match this item).
	 * 
	 */
	public UsableItem(Material mat, String name)
	{
		this.mat = mat;
		this.name = name;
	}
	
	/**
	 * 
	 * @param player The player who used the item
	 * @param item The item the player used (usually same as item in hand, use this for checking not get the players item in hand)
	 * @param block The block the player clicked (may be null)
	 * @param entity The entity the player clicked (may be null)
	 * @param rightClick True if right click, false if left click
	 * @return Return {@linkplain EventAction} to decide how the origin even should be handled. 
	 */
	public abstract EventAction click(Player player, ItemStack item, Block block, Entity entity, boolean rightClick);
	
	
	/**
	 * Start registering clicks for this item
	 * 
	 * @return Returns false if this item was already registered
	 */
	public boolean register()
	{
		return UsableItemManager.getInstance().registerItem(this);
	}
	
	/**
	 * Stop registering clicks for this item
	 * 
	 * @return Returns false if this item was not registered.
	 */
	public boolean unregister()
	{
		return UsableItemManager.getInstance().unregisterItem(this);
	}
	
	/**
	 * @param item ItemStack to test
	 * @return Returns true if this item matches this usable item.
	 */
	public boolean match(ItemStack item)
	{
		if(item == null)
		{
			return false;
		}
		if(item.getType() != mat)
		{
			return false;
		}
		if(name == null)
		{
			return true;
		}
		if(item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(name))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @return Returns an example item which will match and activate this item.
	 */
	public ItemStack getExampleItem()
	{
		ItemStack item = new ItemStack(mat);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		item.setItemMeta(im);
		return item;
	}
}
