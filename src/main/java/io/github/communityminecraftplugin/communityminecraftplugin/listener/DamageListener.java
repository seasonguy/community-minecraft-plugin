package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener{

    /**
     * Cancel general damage
     * TODO: Maybe add some sort of option to enable this.
     * @param event
     */
    @EventHandler
    public void onDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }

}
