package me.ninjego.extendedpets.pet.pets;

import org.bukkit.entity.Player;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.pet.Pet;
import me.ninjego.extendedpets.pet.PetAbility;

public class GuardianPet extends Pet {

	@Override
	public String getName() {
		return "Gaurdian";
	}

	@Override
	public String getDisplayName() {
		return "§bGaurdian";
	}

	@Override
	public String headValue() {
		return "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBiZjM0YTcxZTc3MTViNmJhNTJkNWRkMWJhZTVjYjg1Zjc3M2RjOWIwZDQ1N2I0YmZjNWY5ZGQzY2M3Yzk0In19fQ==";
	}

	@Override
	public PetAbility getAbility(Player plr) {
		return ExtendedPets.getInstance().getAbilityManager().getNoneAbility();
	}

	@Override
	public int getValue() {
		return 15;
	}

}
