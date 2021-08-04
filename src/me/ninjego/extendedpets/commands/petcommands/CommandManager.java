package me.ninjego.extendedpets.commands.petcommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ninjego.extendedpets.commands.petcommands.impl.CheckCommand;
import me.ninjego.extendedpets.commands.petcommands.impl.GetCommand;
import me.ninjego.extendedpets.commands.petcommands.impl.GiveCommand;
import me.ninjego.extendedpets.commands.petcommands.impl.ReloadCommand;
import me.ninjego.extendedpets.commands.petcommands.impl.RemoveCommand;
import me.ninjego.extendedpets.commands.petcommands.impl.SetCommand;
import net.md_5.bungee.api.ChatColor;

public class CommandManager implements CommandExecutor {

	public List<SubCommand> commandList = new ArrayList<SubCommand>();
	
	public CommandManager() {
		commandList.add(new GetCommand());
		commandList.add(new RemoveCommand());
		commandList.add(new GiveCommand());
		commandList.add(new CheckCommand());
		commandList.add(new SetCommand());
		commandList.add(new ReloadCommand());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player plr = (Player) sender;
		
		if(args.length > 0) {
			for(SubCommand command : commandList) {
				if(args[0].equalsIgnoreCase(command.getName())) {
					if(plr.hasPermission("pet." + command.getName().toLowerCase())) {
						command.perform(plr, args);	
					} else {
						plr.sendMessage(ChatColor.RED + "You do not have permissions to execute this command.");
						return true;
					}
				}
			}
		} else {
			for(SubCommand command : commandList) {
				plr.sendMessage(ChatColor.GREEN + command.getName() + " - "+ ChatColor.DARK_GREEN + command.getUsage());
			}
			return true;
		}
	
		return true;
	}
	
}
