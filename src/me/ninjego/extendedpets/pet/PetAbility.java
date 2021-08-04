package me.ninjego.extendedpets.pet;

import org.bukkit.entity.Player;

public abstract class PetAbility {

	public abstract String getName();
	
	public abstract String getDescription(int value);
	
	public abstract void onEquipped(Player plr, Pet pet, int value);
	
	public abstract void onDequipped(Player plr, Pet pet, int value);
	
}
