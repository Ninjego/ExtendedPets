package me.ninjego.extendedpets.managers;

import java.util.ArrayList;
import java.util.List;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.pet.PetAbility;
import me.ninjego.extendedpets.pet.abilities.NoneAbility;

public class AbilityManager {

	public List<PetAbility> abilityList = new ArrayList<PetAbility>();
	
	private NoneAbility noneAbility;
	
	public void load() {
		noneAbility = new NoneAbility();
		register(noneAbility);
	}

	private void register(PetAbility ability) {
		abilityList.add(ability);
	}
	
	public NoneAbility getNoneAbility() {
		return noneAbility;
	}
	
	public static PetAbility getAbility(String name) {
		return ExtendedPets.getInstance().getAbilityManager().abilityList.stream().filter(ab -> ab.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	
}
