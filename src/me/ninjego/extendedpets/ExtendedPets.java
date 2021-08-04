package me.ninjego.extendedpets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.ninjego.extendedpets.commands.PetsCommand;
import me.ninjego.extendedpets.commands.petcommands.CommandManager;
import me.ninjego.extendedpets.data.PetData;
import me.ninjego.extendedpets.listeners.InteractListener;
import me.ninjego.extendedpets.listeners.MenuClickListener;
import me.ninjego.extendedpets.listeners.PlayerJoinListener;
import me.ninjego.extendedpets.managers.AbilityManager;
import me.ninjego.extendedpets.managers.PetManager;
import me.ninjego.extendedpets.menus.PlayerMenu;
import me.ninjego.extendedpets.pet.Pet;
import me.ninjego.extendedpets.pet.PetAbility;
import me.ninjego.extendedpets.pet.PlayerUtilities;

public class ExtendedPets extends JavaPlugin {
	
	private static ExtendedPets instance;
	
	private PetData data;
	
	private AbilityManager abilityManager;
	
	private PetManager petManager;
	
	public static HashMap<Player, Pet> selectedList = new HashMap<Player, Pet>();
	
	public static ExtendedPets getInstance() {
		return instance;
	}
	
	public AbilityManager getAbilityManager() {
		return abilityManager;
	}
	
	public PetManager getPetManager() {
		return petManager;
	}
	
	public PetData getData() {
		return data;
	}
	
	private static final HashMap<Player, PlayerMenu> playerMenuMap = new HashMap<>();
	private static final HashMap<Player, PlayerUtilities> playerUtilMap = new HashMap<>();
	
	@Override
	public void onEnable() {
		instance = this;
		data = new PetData();
		data.saveDefaultConfig();
		
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
		
		new PetsCommand();
		new PlayerJoinListener();
		new InteractListener();
		
		abilityManager = new AbilityManager();
		abilityManager.load();
		
		petManager = new PetManager();
		petManager.load();
		
		getCommand("pet").setExecutor(new CommandManager());
		
		/*data.getConfig().getConfigurationSection("users").getKeys(false).forEach(uuid -> {
			for(Player plr : Bukkit.getOnlinePlayers()) {
				if(plr.getUniqueId().equals(UUID.fromString(uuid))) {
					Pet pet = petManager.getPet(UUID.fromString(uuid));
					
					if(pet == null) {
						return;
					}
					
					if(getPlayerUtilities(plr).getPetList().contains(pet)) {
						selectedList.put(plr, pet);
						
						selectedList.get(plr).getAbility(plr).onEquipped(plr, selectedList.get(plr), selectedList.get(plr).getValue());	
					}
				}
			}
		});*/
	}
	
	@Override
	public void onDisable() {
		for(Entry<Player, Pet> entry : selectedList.entrySet()) {
			entry.getValue().getAbility(entry.getKey()).onDequipped(entry.getKey(), entry.getValue(), entry.getValue().getValue());
		}
	}
	
	public static PlayerMenu getPlayerMenu(Player plr) {
		PlayerMenu menu;
		if(!(playerMenuMap.containsKey(plr))) {
			menu = new PlayerMenu(plr);
			playerMenuMap.put(plr, menu);
			
			return menu;
		} else {
			return playerMenuMap.get(plr);
		}
	}
	
	public static String getString(String location, Player plr) {
		String str = getInstance().getConfig().getString(location).replaceAll("%player%", plr.getName());
		return ChatColor.translateAlternateColorCodes('&', str);
	}
	
	public static String getString(String location) {
		String str = getInstance().getConfig().getString(location);
		return ChatColor.translateAlternateColorCodes('&', str);
	}
	
	public static String generateString(String string, Player plr, Pet pet) {
		String str = string.replaceAll("%player%", plr.getName()).replaceAll("%ability%", pet.getAbility(plr).getName()).replaceAll("%description%", pet.getAbility(plr).getDescription(pet.getValue()));
		return ChatColor.translateAlternateColorCodes('&', str);
	}
	
	public static PlayerUtilities getPlayerUtilities(Player plr) {
		PlayerUtilities util;
		if(!(playerUtilMap.containsKey(plr))) {
			
			List<PetAbility> abil = new ArrayList<PetAbility>();
			
			for(PetAbility ab : ExtendedPets.getInstance().getAbilityManager().abilityList) {
				try {
					abil.add(ab.getClass().newInstance());
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			
			List<Pet> pets = new ArrayList<Pet>();
			
			if(ExtendedPets.getInstance().getData().getConfig().contains("users." + plr.getUniqueId() + ".pets")) {
				for(String name : ExtendedPets.getInstance().getData().getConfig().getStringList("users."+ plr.getUniqueId() + ".pets")) {
					Pet pet = ExtendedPets.getInstance().getPetManager().getPet(name);
					if(pet == null) {
						break;
					}
					pets.add(pet);
				}
			}
			
			util = new PlayerUtilities(plr, abil, pets);
			playerUtilMap.put(plr, util);
			return util;
		} else {
			return playerUtilMap.get(plr);
		}
	}
	
	public static void updateUtility(Player plr) {
		if(playerUtilMap.containsKey(plr)) {
			playerUtilMap.remove(plr);
			getPlayerUtilities(plr);
		}
	}
	
	public static boolean checkEquipped(Player player, Pet pet) {
		if(selectedList.containsKey(player)) { 
			if(selectedList.get(player).equals(pet)) {
				return true;
			}
		}
		return false;
	}
	
}
