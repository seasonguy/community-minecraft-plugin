package io.github.communityminecraftplugin.communityminecraftplugin.usableitems.items;

import java.util.function.BiFunction;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import io.github.communityminecraftplugin.communityminecraftplugin.usableitems.UsableItem;
import net.md_5.bungee.api.ChatColor;

public class WavyStick extends UsableItem implements Listener {
	private Plugin plugin;
	private BiFunction<Double, Double, Double> sin = y(1, 2*Math.PI / 10d);
	public WavyStick(Plugin plugin) {
		super(Material.STICK, ChatColor.GREEN + "Wavy Stick");
		this.plugin = plugin;
		this.register();
	}

	@Override
	public EventAction click(Player player, ItemStack item, Block block, Entity entity, boolean rightClick) {
		Location og = player.getEyeLocation().clone();
		displayWave(og, 0.25, 0);
		
		return EventAction.NONE;
	}
	
	private void displayWave(Location og, double gran, double time) {
		og = og.clone();
		Vector dir = og.getDirection().clone().normalize().multiply(gran);
		
		for (double i = 0; i < 100; i+=gran) {
			og.add(dir);
			og.add(0, sin.apply(i, time), 0);
			og.getWorld().spawnParticle(Particle.TOTEM, og, 1, 0, 0, 0, 0);
		}
	}
	
	private BiFunction<Double, Double, Double> y(double A, double k) {
		return (x, t) -> {
			return A*Math.sin(k*x);
		};
	}
}
