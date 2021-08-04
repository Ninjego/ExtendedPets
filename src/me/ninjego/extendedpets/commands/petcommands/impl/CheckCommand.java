package me.ninjego.extendedpets.commands.petcommands.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.commands.petcommands.SubCommand;
import me.ninjego.extendedpets.menus.impl.CheckMenu;
import net.md_5.bungee.api.ChatColor;

public class CheckCommand extends SubCommand {

	@Override
	public String getName() {
		return "check";
	}

	@Override
	public String getUsage() {
		return "/pet check <Player>";
	}

	@Override
	public void perform(Player plr, String[] args) {

		if (args.length > 1) {
			Player target = Bukkit.getPlayer(args[1]);

			if (target == null) {
				plr.sendMessage(ChatColor.RED + "[!] This player is not online!");
				return;
			}

			new CheckMenu(ExtendedPets.getPlayerMenu(target)).open(plr);
			
			plr.sendMessage(ChatColor.GREEN + "[!] You are now checking " + ChatColor.DARK_GREEN + target.getName());
			
		} else {
			sendUsage(plr);
			return;
		}

	}

}
