package me.ninjego.extendedpets.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import me.ninjego.extendedpets.menus.Menu;

public class MenuClickListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		InventoryHolder holder = e.getInventory().getHolder();
		
		if(holder instanceof Menu) {
			e.setCancelled(true);
			if(e.getCurrentItem() == null) {
				return;
			}
			
			Menu menu = (Menu) holder;
			
			menu.onClick(e);
		}
	}
	
}
