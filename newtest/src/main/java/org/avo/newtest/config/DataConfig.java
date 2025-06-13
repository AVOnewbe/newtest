package org.avo.newtest.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class DataConfig {

    private final JavaPlugin plugin;

    private File dataFile;
    private FileConfiguration dataConfig;

    public DataConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        setup();
    }

    public void setup() {
        dataFile = new File(plugin.getDataFolder(), "data.yml");

        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs(); // สร้างโฟลเดอร์ถ้ายังไม่มี
            plugin.saveResource("data.yml", false); // คัดลอกไฟล์จาก resources ไปยัง dataFile
        }

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getConfig() {
        return dataConfig;
    }

    public void saveConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }
}
