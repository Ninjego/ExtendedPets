package me.ninjego.extendedpets.pet.abilities;

import org.bukkit.entity.Player;

import me.ninjego.extendedpets.pet.Pet;
import me.ninjego.extendedpets.pet.PetAbility;
import net.md_5.bungee.api.ChatColor;

public class NoneAbility extends PetAbility {

	@Override
	public String getName() {
		return "Unassigned";
	}

	@Override
	public String getDescription(int value) {
		return ChatColor.GRAY + "No ability (Probably being developed)";
	}

	@Override
	public void onEquipped(Player plr, Pet pet, int value) {
		
	}

	@Override
	public void onDequipped(Player plr, Pet pet, int value) {
		
	}

	
	
}
