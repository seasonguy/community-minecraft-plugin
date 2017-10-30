package io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items;

import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItem;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author monst12
 * @date 30.10.2017
 *
 * Add pumpkin thrower item
 */
public class PumpkinItem extends UsableItem implements Listener {

    private static final long COOLDOWN = 4*1000;

    private final Map<UUID, Long> cooldowns = new HashMap<>();
    private final Random random;

    /**
     * A usable item which can be used to trigger code on different events
     * @see UsableItem
     */
    public PumpkinItem(JavaPlugin plugin) {
        super(Material.PUMPKIN, ChatColor.GOLD+"Pumpkin Thrower");
        this.random = new Random();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.register();
    }

    @Override
    public EventAction click(Player player, ItemStack item, Block block, Entity entity, boolean rightClick) {

        //silently c&p from LeapItem
        long nextUse = cooldowns.getOrDefault(player.getUniqueId(), 0L);
        if(nextUse > System.currentTimeMillis())
        {
            double diff = (nextUse - System.currentTimeMillis()) / 1000.0;
            DecimalFormat format = new DecimalFormat("0.00");
            player.sendMessage(ChatColor.GREEN + "Leap> " + ChatColor.GRAY +
                    "Please wait another " + ChatColor.GOLD + format.format(diff) + "s" + ChatColor.GRAY + " before using this item again.");
            return EventAction.CANCEL;
        }

        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + COOLDOWN);

        //spawn a fallingblock representing the flying pumpkin
        FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(player.getEyeLocation(), new MaterialData(Material.PUMPKIN));
        Vector vec = player.getLocation().getDirection().clone().normalize().multiply(1.2);
        fallingBlock.setVelocity(vec);
        fallingBlock.setHurtEntities(true);
        fallingBlock.setDropItem(false);

        return EventAction.CANCEL;
    }

    /**
     * Listen for the fallingblock to land
     * @param event
     */
    @EventHandler
    public void onBlockForm(EntityChangeBlockEvent event){
        if(event.getEntityType() != EntityType.FALLING_BLOCK) return;
        if(event.getTo() != Material.PUMPKIN) return;
        event.setCancelled(true);

        Location loc = event.getEntity().getLocation();

        //check for surrounding players to set their a pumpkin
        event.getEntity().getNearbyEntities(1,2,1)
                .stream()
                .filter(entity -> entity.getType() == EntityType.PLAYER)
                .forEach(entity ->{

            Player player = (Player) entity;
            player.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
        });

        //drop some loot
        this.dropRandomItems(event.getEntity().getLocation(), Material.PUMPKIN_PIE, 4);
        //spawn some particles
        event.getEntity().getWorld().spawnParticle(
                Particle.TOTEM,
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                10,
                1.0,
                1.0,
                1.0,
                0.4);

        //play some sound
        loc.getWorld().playSound(loc, Sound.ENTITY_BOBBER_SPLASH, 6f, 1);
    }

    /**
     * Spawn N items with a (small) random Vector at a given location.
     * Every item will (theoretically) fly into a different direction.
     * @param location Location where the items should be spawned
     * @param material The Material of all the items
     * @param amount The amount of the items that will be spawned
     */
    private void dropRandomItems(Location location, Material material, int amount){
        ItemStack stack = new ItemStack(material);
        for(int i = 0; i < amount; i++){
            Vector vec = new Vector((0.1*random.nextDouble()+0.1), (0.2*random.nextDouble()+0.2), (0.1*random.nextDouble()+0.1));

            Item item = location.getWorld().dropItem(location, stack);
            item.setItemStack(stack);
            item.setVelocity(vec);
            item.setPickupDelay(200);
            item.setTicksLived(6000);
        }
    }
}
