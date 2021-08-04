package me.ninjego.extendedpets.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.fusesource.jansi.Ansi;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.pet.Pet;
import me.ninjego.extendedpets.pet.PetAbility;
import me.ninjego.extendedpets.pet.pets.CustomPet;
import me.ninjego.extendedpets.pet.pets.GuardianPet;

public class PetManager {

	public List<Pet> petList = new ArrayList<Pet>();

	private CustomPet pet;
	private GuardianPet guardianPet;

	public void load() {
		for(String str : ExtendedPets.getInstance().getConfig().getConfigurationSection("pets").getKeys(false)) {
			String name = str;
			String displayName = ChatColor.translateAlternateColorCodes('&', ExtendedPets.getString("pets." + name + ".name"));
			String headValue = ExtendedPets.getInstance().getConfig().getString("pets." + name + ".value");
			PetAbility ability = AbilityManager.getAbility(ExtendedPets.getInstance().getConfig().getString("pets." + name + ".ability"));
			List<String> lores = new ArrayList<>();
			
			for(String lore : ExtendedPets.getInstance().getConfig().getStringList("pets." + name + ".lores")) {
				lores.add(lore);
			}
			
			int value = ExtendedPets.getInstance().getConfig().getInt("pets." + name + ".value");
			
			pet = new CustomPet(name, displayName, headValue, ability, value);
			registerPet(pet);
		}
		
		guardianPet = new GuardianPet();
		registerPet(guardianPet);
	}

	public Pet getPet(String name) {
		return petList.stream().filter(pet -> pet.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public Pet getDisplay(String displayName) {
		for(Pet petd : petList) {
			if(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', petd.getDisplayName())).equalsIgnoreCase(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', displayName)))) {
				return petd;
			}
		}
		return null;
	}

	public Pet getPet(UUID uuid) {
		return getPet(ExtendedPets.getInstance().getData().getConfig().getString("users." + uuid.toString() + ".selected"));
	}

	public void registerPet(Pet pet) {
		petList.add(pet);
		ExtendedPets.getInstance().getLogger().info(Ansi.ansi().fg(Ansi.Color.MAGENTA).boldOff().toString() + "Successfully loaded: " + Ansi.ansi().fg(Ansi.Color.GREEN).boldOff().toString() + pet.getName() + Ansi.ansi().fg(Ansi.Color.WHITE).boldOff().toString());
	}

}
