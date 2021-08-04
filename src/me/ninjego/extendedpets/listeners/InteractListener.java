package me.ninjego.extendedpets.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.pet.Pet;
import me.ninjego.extendedpets.pet.PlayerUtilities;
import net.md_5.bungee.api.ChatColor;

public class InteractListener implements Listener {

	public InteractListener() {
		ExtendedPets.getInstance().getServer().getPluginManager().registerEvents(this, ExtendedPets.getInstance());
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getItem() != null) {
			Player plr = (Player) e.getPlayer();
			
			for(Pet pet : ExtendedPets.getInstance().getPetManager().petList) {
				if(e.getItem().equals(pet.getPet(plr))) {
					
					e.setCancelled(true);
					
					PlayerUtilities util = ExtendedPets.getPlayerUtilities(plr);
					
					if(!util.getPetList().contains(pet)) {
						if(ExtendedPets.getInstance().getData().getConfig().contains("users." + plr.getUniqueId() + ".pets")) {
							int slots = ExtendedPets.getInstance().getData().getConfig()
									.getInt("users." + plr.getUniqueId() + ".slots");
							
							int pets = ExtendedPets.getInstance().getData().getConfig().getStringList("users." + plr.getUniqueId() + ".pets").size();
							
							if(pets >= slots) {
								plr.sendMessage(ChatColor.RED + "[!] You do not have any free slots!");
								return;
							}	
						}
						
						ItemStack item = e.getItem().clone();
						item.setAmount(1);
						
						plr.getInventory().remove(item);
						
						ExtendedPets.getPlayerUtilities(plr).getPetList().add(pet);
						
						List<String> name = new ArrayList<String>();
						
						if(ExtendedPets.getInstance().getData().getConfig().contains("users." + plr.getUniqueId() + ".pets") && ExtendedPets.getInstance().getData().getConfig().getStringList("users." + plr.getUniqueId() + ".pets") != null) {
							for(String petname : ExtendedPets.getInstance().getData().getConfig().getStringList("users." + plr.getUniqueId() + ".pets")) {
								name.add(petname);
							}
						}
						
						name.add(pet.getName());
						
						ExtendedPets.getInstance().getData().getConfig().set("users." + plr.getUniqueId() + ".pets", name);
						ExtendedPets.getInstance().getData().saveConfig();
						
						plr.sendMessage(ChatColor.GREEN + "[!] You have successfully claimed " + ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', pet.getDisplayName()) + ChatColor.GREEN + " pet");
					} else {
						e.setCancelled(true);
						plr.sendMessage(ChatColor.RED + "[!] You already have this pet");
						return;
					}
				}
			}
		}
	}
	
}
