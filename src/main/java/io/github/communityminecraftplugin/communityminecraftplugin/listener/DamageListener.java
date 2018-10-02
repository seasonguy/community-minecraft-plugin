package io.github.communityminecraftplugin.communityminecraftplugin.listener;

import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;
import org.bukkit.entity.EntityType;
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
        if (event.getEntityType() == EntityType.PLAYER && !Settings.GENERAL_PLAYER_DAMAGE.getBoolean()) {
            event.setCancelled(true);
        }
    }

}
