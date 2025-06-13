package org.avo.newtest.Event;

import org.avo.newtest.Command.InvCommand;
import org.avo.newtest.config.InvConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class InvSave implements Listener {

    private final InvConfig invConfig;

    public InvSave(InvConfig invConfig) {
        this.invConfig = invConfig;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        String title = event.getView().getTitle();

        if (title.equals("§aช่อง§dเก็บ§bของ")) {
            if (event .getPlayer() instanceof Player player) {
                Inventory inventory = event.getInventory();
                invConfig.saveInventory(player.getUniqueId(), inventory.getContents());
            }
        }
    }
}
