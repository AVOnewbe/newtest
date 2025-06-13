package org.avo.newtest.Command;

import org.avo.newtest.config.InvConfig;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.createInventory;

public class InvCommand  implements CommandExecutor {

    private final InvConfig invConfig;

    public InvCommand(JavaPlugin plugin) {
        this.invConfig = new InvConfig(plugin);
    }

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (!(sender instanceof org.bukkit.entity.Player)) {
            sender.sendMessage("§cคำสั่งนี้ใช้ได้เฉพาะผู้เล่นเท่านั้น");
            return true;
        }

        Player player = (Player) sender;

        Inventory inv = createInventory(null, 54, "§aช่อง§dเก็บ§bของ");
        ItemStack[] items = invConfig.loadInventory(player.getUniqueId());
        inv.setContents(items);

        player.openInventory(inv);

        return true;
    }

    public InvConfig getInvConfig() {
        return invConfig;
    }
}
