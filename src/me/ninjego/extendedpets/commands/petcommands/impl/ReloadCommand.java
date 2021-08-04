package me.ninjego.extendedpets.commands.petcommands.impl;

import org.bukkit.entity.Player;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.commands.petcommands.SubCommand;
import net.md_5.bungee.api.ChatColor;

public class ReloadCommand extends SubCommand {

	@Override
	public String getName() {
		return "reload";
	}

	@Override
	public String getUsage() {
		return "/pet reload";
	}

	@Override
	public void perform(Player plr, String[] args) {
		ExtendedPets.getInstance().getData().reloadConfig();
		ExtendedPets.getInstance().reloadConfig();
		plr.sendMessage(ChatColor.GREEN + "[!] Successfully reloaded configs");
	}

}
