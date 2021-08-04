package me.ninjego.extendedpets.commands.petcommands.impl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.commands.petcommands.SubCommand;
import me.ninjego.extendedpets.pet.Pet;
import net.md_5.bungee.api.ChatColor;

public class RemoveCommand extends SubCommand {

	@Override
	public String getName() {
		return "remove";
	}

	@Override
	public String getUsage() {
		return "/pet remove <Pet> <Player>";
	}

	@Override
	public void perform(Player plr, String[] args) {
		if (args.length >= 3) {
			Pet pet = ExtendedPets.getInstance().getPetManager().getPet(args[1]);

			if (pet == null) {
				plr.sendMessage(ChatColor.RED + "[!] This pet doesn't exist");
				return;
			}

			Player target = Bukkit.getPlayerExact(args[2]);

			if (target == null) {
				plr.sendMessage(ChatColor.RED + "[!] This player is not online!");
				return;
			}
			
			if(!ExtendedPets.getPlayerUtilities(target).getPetList().contains(pet)) {
				plr.sendMessage(ChatColor.RED + "[!] " +ChatColor.DARK_RED  + target.getName() + ChatColor.RED + " do not have this pet");
				return;
			}
			
			List<String> names = new ArrayList<String>(ExtendedPets.getInstance().getData().getConfig().getStringList("users." + target.getUniqueId() + ".pets"));
			
			if(names.contains(pet.getName())) {
				names.remove(pet.getName());
			}
			
			if(ExtendedPets.checkEquipped(target, pet)) {
				ExtendedPets.selectedList.remove(target);
			}
			
			ExtendedPets.getInstance().getData().getConfig().set("users." + target.getUniqueId() + ".pets", names);
			ExtendedPets.getInstance().getData().saveConfig();
			ExtendedPets.updateUtility(target);
			
			plr.sendMessage(ChatColor.GREEN + "[!] Successfully removed " + ChatColor.DARK_GREEN + pet.getName() + ChatColor.GREEN + " pet from " + ChatColor.DARK_GREEN + target.getName());
		} else {
			sendUsage(plr);
			return;
		}
	}

}
