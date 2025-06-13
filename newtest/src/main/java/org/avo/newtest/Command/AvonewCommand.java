package org.avo.newtest.Command;

import org.avo.newtest.Newtest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AvonewCommand implements CommandExecutor {

    private final RandomCommand randomCommand;
    private final RandomItemCommand randomItemCommand;
    private final GuiCommand guiCommand; //เตรียมคำสั่ง GUI

    public AvonewCommand(GuiCommand guiCommand, Newtest plugin) {
        this.randomCommand = new RandomCommand(); //เตรียมคำสั่งสุ่ม
        this.randomItemCommand = new RandomItemCommand();
        this.guiCommand = guiCommand;//เตรียมคำสั่งสุ่มไอเท็ม
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§c/avonew คำสั่ง");
            return true;
        }

        if (args[0].equalsIgnoreCase("random")) {
            return randomCommand.onCommand(sender, command, label, args);
        } else if (args[0].equalsIgnoreCase("randomore")) {
            return randomItemCommand.onCommand(sender, command, label, args);
        } else if (args[0].equalsIgnoreCase("gui")) {
            return guiCommand.onCommand(sender, command, label, args);
        }
        sender.sendMessage("§cคำสั่งไม่ถูกต้อง" + args[0]);
        return true;
    }
}
