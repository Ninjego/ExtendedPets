package me.ninjego.extendedpets.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ninjego.extendedpets.ExtendedPets;

public class PetData {

	  private FileConfiguration dataConfig = null;
	    private File configFile = null;

	    public PetData() {
	        saveDefaultConfig();
	    }

	    public void reloadConfig() {
	        if(this.configFile == null)
	            this.configFile = new File(ExtendedPets.getInstance().getDataFolder(), "PetData.yml");

	        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

	        InputStream defaultStream = ExtendedPets.getInstance().getResource("PetData.yml");
	        if(defaultStream != null) {
	            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
	            this.dataConfig.setDefaults(defaultConfig);
	        }
	    }

	    public FileConfiguration getConfig() {
	        if(this.dataConfig == null)
	            reloadConfig();

	        return this.dataConfig;
	    }

	    public void saveConfig() {
	        if(this.dataConfig == null || this.configFile == null)
	            return;

	        try {
	            this.getConfig().save(this.configFile);
	        } catch (IOException e) {
	        	ExtendedPets.getInstance().getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
	        }
	    }

	    public void saveDefaultConfig() {
	        if(configFile == null)
	            this.configFile = new File(ExtendedPets.getInstance().getDataFolder(), "PetData.yml");

	        if(!this.configFile.exists()) {
	        	ExtendedPets.getInstance().saveResource("PetData.yml", false);
	        }
	    }
	
}
