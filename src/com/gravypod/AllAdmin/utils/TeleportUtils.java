package com.gravypod.AllAdmin.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import com.gravypod.AllAdmin.AllAdmin;
import com.gravypod.AllAdmin.user.AllAdminUser;

public class TeleportUtils {
	
	public static void delayedTeleport(final AllAdminUser player, final Location toGo, long time) {
		
		player.sendMessage(ChatColor.AQUA + "Do not move for " + time + " seconds");
		
		player.updateLastLocation();
		
		final Location playersLocation = player.getLastLocation();
		
		AllAdmin.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(AllAdmin.getInstance(), new Runnable() {

			@Override
            public void run() {
				player.updateLastLocation();
				
				if ((playersLocation != player.getLastLocation()))
					player.sendMessage(ChatColor.RED + "You have not been teleported. You moved");
					
				player.getBukkitPlayer().teleport(safeLocation(toGo));
				
				player.sendMessage(ChatColor.RED + "You have been teleported");
				
            }
			
		}, time);
		
	}
	
	public static Location safeLocation(Location spawnLocation) {
		
		Location blockLocation = spawnLocation;
		double yLoc = blockLocation.getY();
		
		//find the first non air block below us
		while (blockLocation.getBlock().getType() != Material.AIR) {
		    blockLocation.setY(yLoc + 1);
		}
		
		// set to 1 block up so we are not sunk in the ground
		blockLocation.setY(yLoc + 1);
		
		return blockLocation;
		
	}
	
}