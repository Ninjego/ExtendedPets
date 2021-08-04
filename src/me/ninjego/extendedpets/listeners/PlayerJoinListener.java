package me.ninjego.extendedpets.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.pet.Pet;

public class PlayerJoinListener implements Listener {

	public PlayerJoinListener() {
		ExtendedPets.getInstance().getServer().getPluginManager().registerEvents(this, ExtendedPets.getInstance());
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player plr = (Player) e.getPlayer();
		
		if(!ExtendedPets.getInstance().getData().getConfig().contains("users." + plr.getUniqueId() + ".slots")) {
			ExtendedPets.getInstance().getData().getConfig().set("users." + plr.getUniqueId() + ".slots", 1);
			ExtendedPets.getInstance().getData().saveConfig();
		}
		
		Pet pet = ExtendedPets.getInstance().getPetManager().getPet(plr.getUniqueId());
		
		if (pet == null) {
			return;
		}

		if(ExtendedPets.getPlayerUtilities(plr).getPetList().contains(pet)) {
			pet.getAbility(plr).onEquipped(plr, pet, pet.getValue());

			ExtendedPets.selectedList.put(plr, pet);	
		}
	}

}
