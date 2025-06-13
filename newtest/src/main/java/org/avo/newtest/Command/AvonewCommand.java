package org.avo.newtest.Command;

import org.avo.newtest.Newtest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AvonewCommand implements CommandExecutor {

    private final RandomCommand randomCommand;
    private final RandomItemCommand randomItemCommand;
    private final GuiCommand guiCommand;
    private final InvCommand invCommand;

    public AvonewCommand(GuiCommand guiCommand, InvCommand invCommand, Newtest plugin) {
        this.randomCommand = new RandomCommand();
        this.randomItemCommand = new RandomItemCommand();
        this.guiCommand = guiCommand;
        this.invCommand = invCommand;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§c/avonew คำสั่ง");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "random":
                return randomCommand.onCommand(sender, command, label, args);
            case "randomore":
                return randomItemCommand.onCommand(sender, command, label, args);
            case "gui":
                return guiCommand.onCommand(sender, command, label, args);
            case "inv":
                return invCommand.onCommand(sender, command, label, args);
            default:
                sender.sendMessage("§cคำสั่งไม่ถูกต้อง: " + args[0]);
                return true;
        }
    }
}
