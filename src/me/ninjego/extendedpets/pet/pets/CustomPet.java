package me.ninjego.extendedpets.pet.pets;

import org.bukkit.entity.Player;

import me.ninjego.extendedpets.pet.Pet;
import me.ninjego.extendedpets.pet.PetAbility;

public class CustomPet extends Pet {

	private String name,displayName,headValue;
	private PetAbility ability;
	private int value;
	
	public CustomPet(String name, String displayName, String headValue, PetAbility ability, int value) {
		this.name = name;
		this.displayName = displayName;
		this.headValue = headValue;
		this.ability = ability;
		this.value = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String headValue() {
		return headValue;
	}

	@Override
	public PetAbility getAbility(Player plr) {
		return ability;
	}

	@Override
	public int getValue() {
		return value;
	}

}
