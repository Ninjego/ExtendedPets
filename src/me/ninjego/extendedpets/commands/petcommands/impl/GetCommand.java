package me.ninjego.extendedpets.commands.petcommands.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.commands.petcommands.SubCommand;
import me.ninjego.extendedpets.pet.Pet;
import net.md_5.bungee.api.ChatColor;

public class GetCommand extends SubCommand {

	@Override
	public String getName() {
		return "get";
	}
	
	@Override
	public String getUsage() {
		return "/pet get <Pet>";
	}

	@Override
	public void perform(Player plr, String[] args) {
		 if(args.length == 2) {
			 Pet pet = ExtendedPets.getInstance().getPetManager().getPet(args[1]);
			 
			 if(pet == null) {
				 plr.sendMessage(ChatColor.RED + "[!] This pet doesn't exist");
				 return;
			 }
			 
			 plr.getInventory().addItem(pet.getPet(plr));
			 plr.sendMessage(ChatColor.GREEN + "You have been given a " + ChatColor.DARK_GREEN + pet.getName()+ ChatColor.GREEN + " pet");
		 } 
		 else if(args.length >= 3) {
			 Pet pet = ExtendedPets.getInstance().getPetManager().getPet(args[1]);
			 
			 if(pet == null) {
				 plr.sendMessage(ChatColor.RED + "[!] This pet doesn't exist");
				 return;
			 }
			 
			 Player target = Bukkit.getPlayerExact(args[2]);
			 
			 if(target == null) {
				 plr.sendMessage(ChatColor.RED + "[!] This player is not online!");
				 return;
			 }
			 
			 target.getInventory().addItem(pet.getPet(target));
			 plr.sendMessage(ChatColor.GREEN + "You have given a " + ChatColor.DARK_GREEN  + pet.getName() + ChatColor.GREEN +" pet to " + ChatColor.DARK_GREEN + target.getName());
			 target.sendMessage(ChatColor.GREEN + "You have been given a " + ChatColor.DARK_GREEN + pet.getName()+ ChatColor.GREEN + " pet");

		 }
		 else {
			 sendUsage(plr);
			 return;
		 }
	}


}
