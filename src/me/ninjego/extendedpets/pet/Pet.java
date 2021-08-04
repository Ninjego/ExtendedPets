package me.ninjego.extendedpets.pet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.ninjego.extendedpets.ExtendedPets;
import net.md_5.bungee.api.ChatColor;

public abstract class Pet {
	
	private List<String> lores;
	
	public abstract String getName();
	
	public abstract String getDisplayName();
	
	public void setLores(List<String> lores) {
		this.lores = lores;
	}
	
	public List<String> getLores() {
		return lores;
	}
	
	public abstract String headValue();
	
	public abstract PetAbility getAbility(Player plr);
	
	public abstract int getValue();
	
	public ItemStack getPet(Player plr) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if (headValue().isEmpty()) return head;
        
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        
        headMeta.setDisplayName(ChatColor.GREEN + ChatColor.translateAlternateColorCodes('&', getDisplayName()));
        
        List<String> lores = new ArrayList<String>();

        if(getLores() != null) {
        	for(String lore : getLores()) {
        		lores.add(ExtendedPets.generateString(lore, plr, this));
        	}
        } else {
            lores.add(ChatColor.GRAY + "Ability: " + ChatColor.GREEN + getAbility(plr).getName());
            lores.add(getAbility(plr).getDescription(getValue()));	
        }
        
        headMeta.setLore(lores);
        
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        
        profile.getProperties().put("textures", new Property("textures", headValue()));
        
        try
        {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
            
        }
        catch (IllegalArgumentException|NoSuchFieldException|SecurityException | IllegalAccessException error)
        {
            error.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
	}
}
