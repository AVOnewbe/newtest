package org.avo.newtest.Command;

import org.avo.newtest.Newtest;
import org.avo.newtest.config.GuiConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.createInventory;

public class GuiCommand implements CommandExecutor {

    private final GuiConfig guiConfig;

    public GuiCommand(Newtest plugin) {
        this.guiConfig = new GuiConfig(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cคำสั่งนี้ใช้ได้เฉพาะผู้เล่นเท่านั้น");
            return true;
        }

        Player player = (Player) sender;
        Inventory gui = createInventory(null, 54, "§aฉ§bล§eา§dม");

        ItemStack[] items = guiConfig.loadInventory();
        gui.setContents(items);

        player.openInventory(gui);
        return true;
    }

    public GuiConfig getGuiConfig() {
        return guiConfig;
    }
}
