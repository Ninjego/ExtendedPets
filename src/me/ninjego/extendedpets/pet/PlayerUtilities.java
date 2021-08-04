package me.ninjego.extendedpets.pet;

import java.util.List;

import org.bukkit.entity.Player;

public class PlayerUtilities {

	private List<PetAbility> abilityList;
	private List<Pet> petList;
	private Player player;

	public PlayerUtilities(Player player, List<PetAbility> abilityList, List<Pet> petList) {
		this.abilityList = abilityList;
		this.player = player;
		this.petList = petList;
	}

	public List<PetAbility> getAbilityList() {
		return abilityList;
	}

	public Player getPlayer() {
		return player;
	}

	public void setAbilityList(List<PetAbility> abilityList) {
		this.abilityList = abilityList;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public List<Pet> getPetList() {
		return petList;
	}
	
	public void setPetList(List<Pet> petList) {
		this.petList = petList;
	}
	
	public PetAbility getAbility(String name) {
		return abilityList.stream().filter(ability -> ability.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public PetAbility getAbility(Class<?> clazz) {
		return abilityList.stream().filter(ability -> ability.getClass().equals(clazz)).findFirst().orElse(null);
	}

}
