package me.ninjego.extendedpets.commands.petcommands;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public abstract class SubCommand {

	public abstract String getName();
	
	public abstract String getUsage();
	
	public abstract void perform(Player plr, String[] args);
	
	public void sendUsage(Player plr) {
		plr.sendMessage(ChatColor.RED + "[!] " + getUsage());
	}
}
