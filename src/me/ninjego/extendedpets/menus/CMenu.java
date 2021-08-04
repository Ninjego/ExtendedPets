package me.ninjego.extendedpets.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public abstract class CMenu extends PageMenu {

	public CMenu(PlayerMenu playerMenu, int maxItemsPerPage) {
		super(playerMenu, maxItemsPerPage);
	}

	public void open(Player plr) {
		inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

		setMenuItems();

		plr.openInventory(inventory);
	}

}
