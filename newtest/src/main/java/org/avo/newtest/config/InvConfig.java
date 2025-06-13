package org.avo.newtest.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public class InvConfig {

    private final JavaPlugin plugin;
    private final File file;
    private final FileConfiguration config;

    public InvConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "inv.yml");

        if (!file.exists()) {
            plugin.saveResource("inv.yml", false);
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveInventory(UUID uuid, ItemStack[] contents) {
        String basePath = "inventory." + uuid;
        config.set(basePath, null);

        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null) {
                config.set(basePath + "." + i, item);
            }
        }
        try {
            config.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ItemStack[] loadInventory(UUID uuid) {
        String basePath = "inventory." + uuid;
        ItemStack[] items = new ItemStack[54];

        if (config.contains(basePath)) {
            for (String key : config.getConfigurationSection(basePath).getKeys(false)) {
                int slot = Integer.parseInt(key);
                items[slot] = config.getItemStack(basePath + "." + key);
            }
        }
        return items;
    }
}
