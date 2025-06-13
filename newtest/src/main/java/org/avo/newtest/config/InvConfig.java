package org.avo.newtest.config;

import org.bukkit.configuration.file.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public class InvConfig {

    private final File file;
    private final FileConfiguration config;

    public InvConfig(JavaPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), "inv.yml");

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile(); // แก้จาก saveResource เป็นสร้างไฟล์เปล่าแทน
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveInventory(UUID uuid, ItemStack[] contents) {
        String basePath = "inventory." + uuid;
        config.set(basePath, null); // ล้างของเก่า

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
