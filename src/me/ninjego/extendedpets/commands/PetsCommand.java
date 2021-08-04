package me.ninjego.extendedpets.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.menus.impl.PetMenu;
import net.md_5.bungee.api.ChatColor;

public class PetsCommand implements CommandExecutor {

	public PetsCommand() {
		ExtendedPets.getInstance().getCommand("pets").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player plr = (Player) sender;
		
		new PetMenu(ExtendedPets.getPlayerMenu(plr)).open();
		
		plr.sendMessage(ChatColor.GREEN + "Successfully opened Pets Menu!");
		
		return true;
	}

}
