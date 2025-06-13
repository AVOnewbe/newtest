package org.avo.newtest.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class GuiConfig {

    private final File file;
    private final FileConfiguration config;
    private final JavaPlugin plugin;

    public GuiConfig(JavaPlugin plugin) {
        this.plugin = plugin; //รับตัวแปร plugin จากคลาสหลัก

        //gui.yml
        file = new File(plugin.getDataFolder(), "gui.yml");

        if (!file.exists()) {
            plugin.saveResource("gui.yml", false); // คัดลอกไฟล์จาก resources ไปยัง dataFile
        }

        this.config = YamlConfiguration.loadConfiguration(file);

        // ตรวจสอบว่ามีคีย์ "inventory" หรือไม่ ถ้าไม่มีให้สร้าง
        if (!config.contains("inventory")) {
            config.createSection("inventory");
            save(); // บันทึกการเปลี่ยนแปลง
        }
    }

    public void saveInventory(ItemStack[] contents) {
        config.set("inventory", null);
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null) {
                config.set("inventory." + i, item);
            }
        }
        save();
    }

    public ItemStack[] loadInventory() {
        ItemStack[] items = new ItemStack[54];

        if (config.contains("inventory")) {
            for (String key : config.getConfigurationSection("inventory").getKeys(false)) {
                int slot = Integer.parseInt(key);
                items[slot] = config.getItemStack("inventory." + key);
            }
        }
        return items;
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
