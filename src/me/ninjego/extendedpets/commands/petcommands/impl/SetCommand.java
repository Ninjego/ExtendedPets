package me.ninjego.extendedpets.commands.petcommands.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.commands.petcommands.SubCommand;
import net.md_5.bungee.api.ChatColor;

public class SetCommand extends SubCommand {

	@Override
	public String getName() {
		return "set";
	}
	
	@Override
	public String getUsage() {
		return "/pet set slots <number> <player>";
	}
	
	@Override
	public void perform(Player plr, String[] args) {
		if(args.length < 3) {
			return;
		} else if(args.length == 3) {
			if(args[1].equalsIgnoreCase("slots")) {
				int nt = 1;
				
				try {
					nt = Integer.parseInt(args[2]);
				} catch (Exception e) {
					plr.sendMessage(ChatColor.RED + "[!] Invalid amount");
				}

				ExtendedPets.getInstance().getData().getConfig().set("users." + plr.getUniqueId() + ".slots", nt);
				ExtendedPets.getInstance().getData().saveConfig();
				plr.sendMessage(ChatColor.GREEN + "[!] You have successfully set your slots!");
				return;
			} else if(args.length == 4) {
				int nt = 1;
				
				try {
					nt = Integer.parseInt(args[2]);
				} catch (Exception e) {
					plr.sendMessage(ChatColor.RED + "[!] Invalid amount");
				}
				
				Player target = Bukkit.getPlayerExact(args[3]);
				if(target == null) {
					plr.sendMessage(ChatColor.RED + "[!] The selected player is not online");
					return;
				}

				ExtendedPets.getInstance().getData().getConfig().set("users." + target.getUniqueId() + ".slots", nt);
				ExtendedPets.getInstance().getData().saveConfig();
				plr.sendMessage(ChatColor.GREEN + "[!] You have successfully set " + ChatColor.DARK_GREEN + target.getName() + "s" + ChatColor.GREEN + " slots!");
				target.sendMessage(ChatColor.GREEN + "[!] Someone has set your slots to " + ChatColor.DARK_GREEN + nt);
				return;
			}
			else {
				sendUsage(plr);
			}
		}
	}
	
}
