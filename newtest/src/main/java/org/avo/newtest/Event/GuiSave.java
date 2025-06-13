package org.avo.newtest.Event;

import org.avo.newtest.Command.GuiCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiSave implements Listener {

    private final GuiCommand guiCommand;

    public GuiSave (GuiCommand guiCommand) {
        this.guiCommand = guiCommand;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().equals("§aฉ§bล§eา§dม")) {
            guiCommand.getGuiConfig().saveInventory(event.getInventory().getContents());
        }
    }
}
