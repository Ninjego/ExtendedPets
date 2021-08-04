package me.ninjego.extendedpets.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.pet.Pet;

public class PlayerLeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player plr = (Player) e.getPlayer();
		
		if(ExtendedPets.selectedList.containsKey(plr)) {
			Pet pet = ExtendedPets.selectedList.get(plr);
			
			pet.getAbility(plr).onDequipped(plr, pet, pet.getValue());
		}
	}
	
}
