package me.ninjego.extendedpets.menus.impl;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.ninjego.extendedpets.ExtendedPets;
import me.ninjego.extendedpets.menus.PageMenu;
import me.ninjego.extendedpets.menus.PlayerMenu;
import me.ninjego.extendedpets.pet.Pet;
import net.md_5.bungee.api.ChatColor;

public class PetMenu extends PageMenu {

	public PetMenu(PlayerMenu playerMenu) {
		super(playerMenu, ExtendedPets.getInstance().getData().getConfig()
				.getInt("users." + playerMenu.getOwner().getUniqueId() + ".slots"));
	}

	@Override
	public String getMenuName() {
		return ChatColor.GREEN + "Pet Menu";
	}

	@Override
	public int getSlots() {
		return 18;
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		Player plr = (Player) event.getWhoClicked();

		ArrayList<Pet> itemList = (ArrayList<Pet>) ExtendedPets.getPlayerUtilities(plr).getPetList();

		if (event.getCurrentItem().getType().equals(Material.ARROW)) {
			if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Next Page")) {
				if (!((index + 1) >= itemList.size())) {
					page = page + 1;
					super.open();
				} else {
					event.getWhoClicked().sendMessage(ChatColor.GRAY + "You are on the last page.");
				}
			} else if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())
					.equalsIgnoreCase("Previous Page")) {
				if (page == 0) {
					event.getWhoClicked().sendMessage(ChatColor.GRAY + "You are on the first page.");
				} else {
					page = page - 1;
					super.open();
				}
			}
		} else if (event.getCurrentItem().getType().equals(Material.SKULL_ITEM)) {
			if (event.getRawSlot() == 13) {
				return;
			}

			Pet pet = ExtendedPets.getInstance().getPetManager()
					.getDisplay(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));

			System.out.println(pet.getDisplayName());

			if (ExtendedPets.selectedList.containsKey(plr)) {
				Pet selectedPet = ExtendedPets.selectedList.get(plr);
				if (selectedPet.equals(pet)) {
					plr.closeInventory();

					plr.sendMessage(ChatColor.RED + "You have already selected this Pet");

					return;
				}
			}

			if (ExtendedPets.selectedList.containsKey(plr)) {
				ExtendedPets.selectedList.get(plr).getAbility(plr).onDequipped(plr, ExtendedPets.selectedList.get(plr),
						pet.getValue());
			}

			ExtendedPets.selectedList.put(plr, pet);

			ExtendedPets.getInstance().getData().getConfig().set("users." + plr.getUniqueId() + ".selected",
					ChatColor.stripColor(pet.getName()));
			ExtendedPets.getInstance().getData().saveConfig();

			ExtendedPets.selectedList.get(plr).getAbility(plr).onEquipped(plr, ExtendedPets.selectedList.get(plr),
					ExtendedPets.selectedList.get(plr).getValue());

			plr.closeInventory();
			plr.sendMessage(ChatColor.GREEN + "Successfully equipped " + ChatColor.DARK_GREEN + pet.getName());
		}

	}

	@Override
	public void setMenuItems() {

		ArrayList<Pet> itemList = (ArrayList<Pet>) ExtendedPets.getPlayerUtilities(this.playerMenu.getOwner())
				.getPetList();

		fillBottom();

		ItemStack next = makeItem(Material.ARROW, ChatColor.GRAY + "Next Page");
		ItemStack previous = makeItem(Material.ARROW, ChatColor.GRAY + "Previous Page");

		getInventory().setItem(12, previous);
		getInventory().setItem(14, next);
		
		getInventory().setItem(16, makeItem(Material.DIAMOND, ChatColor.BLUE + "" + ChatColor.BOLD + "Upgrade Slots", ChatColor.GRAY + ""));

		if (ExtendedPets.selectedList.containsKey(this.playerMenu.getOwner())) {
			ItemStack selected = ExtendedPets.selectedList.get(this.playerMenu.getOwner())
					.getPet(playerMenu.getOwner());
			getInventory().setItem(13, selected);
		} else {
			ItemStack selected = makeItem(Material.BARRIER, ChatColor.RED + "Unselected");
			getInventory().setItem(13, selected);
		}

		for (int i = 0; i < 9; i++) {
			getInventory().setItem(i, makeItem(Material.STAINED_GLASS_PANE, 14, ChatColor.RED + "" + ChatColor.BOLD + "*LOCKED*"));
		}
		
		int slots = ExtendedPets.getInstance().getData().getConfig()
				.getInt("users." + playerMenu.getOwner().getUniqueId() + ".slots");
		
		int max = getMaxItemsPerPage();
		
		if(max > 9) {
			max = 9;
		}
		
		for(int i = 0; i < max; i++) {
			index = max * page + i;
			if(index >= slots) break;
			inventory.setItem(i, new ItemStack(Material.AIR));
		}

		if (itemList != null && !itemList.isEmpty()) {
			for (int i = 0; i < max; i++) {
				index = max * page + i;
				if (index >= itemList.size())
					break;
				if (itemList.get(index) != null) {
					inventory.addItem(itemList.get(index).getPet(playerMenu.getOwner()));
				}
			}
		}
	}

}
