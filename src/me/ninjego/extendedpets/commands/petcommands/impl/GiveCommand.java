package me.ninjego.extendedpets.commands.petcommands.impl;

public class GiveCommand extends GetCommand {

	@Override
	public String getName() {
		return "give";
	}
	
	@Override
	public String getUsage() {
		return "/pet give <Pet>";
	}
	
}
