package me.ninjego.extendedpets.menus.impl;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.menus.CMenu;
import me.ninjego.extendedpets.menus.PlayerMenu;
import me.ninjego.extendedpets.pet.Pet;
import net.md_5.bungee.api.ChatColor;

public class CheckMenu extends CMenu {

	public CheckMenu(PlayerMenu playerMenu) {
		super(playerMenu, 45);
	}

	@Override
	public String getMenuName() {
		return "Check Menu";
	}

	@Override
	public int getSlots() {
		return 54;
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		
		Player plr = (Player) event.getWhoClicked();
		
		ArrayList<Pet> itemList = (ArrayList<Pet>) ExtendedPets.getPlayerUtilities(plr).getPetList();
		
		if(event.getCurrentItem().getType().equals(Material.ARROW)) {
			if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Next Page")) {
				if(!((index + 1) >= itemList.size())) {
					page = page + 1;
					super.open();
				} else {
					event.getWhoClicked().sendMessage(ChatColor.GRAY + "You are on the last page.");
				}
			} else if(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Previous Page")) {
				if(page == 0) {
					event.getWhoClicked().sendMessage(ChatColor.GRAY + "You are on the first page.");
				} else {
					page = page -1;
					super.open();	
				}
			}
		}
		
	}
	
	@Override
	public void setMenuItems() {
		
		ArrayList<Pet> itemList = (ArrayList<Pet>) ExtendedPets.getPlayerUtilities(this.playerMenu.getOwner()).getPetList();
		
		fillBottom();
		
		ItemStack next = makeItem(Material.ARROW, ChatColor.GRAY + "Next Page");
		ItemStack previous = makeItem(Material.ARROW, ChatColor.GRAY + "Previous Page");
		
		getInventory().setItem(48, previous);
		getInventory().setItem(50, next);
		
		if(ExtendedPets.selectedList.containsKey(this.playerMenu.getOwner())) {
			ItemStack selected = ExtendedPets.selectedList.get(this.playerMenu.getOwner()).getPet(playerMenu.getOwner());
			getInventory().setItem(49, selected);	
		} else {
			ItemStack selected = makeItem(Material.BARRIER, ChatColor.RED + "Unselected");
			getInventory().setItem(49, selected);
		}
		
		if(itemList != null && !itemList.isEmpty()) {
			for(int i = 0; i < getMaxItemsPerPage(); i++) {
				index = getMaxItemsPerPage() * page + i;
				if(index >= itemList.size()) break;
				if(itemList.get(index) != null) {
					inventory.addItem(itemList.get(index).getPet(playerMenu.getOwner()));
				}
			}
		}
	}
}
