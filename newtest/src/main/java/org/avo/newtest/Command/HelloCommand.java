package org.avo.newtest.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("ของคน");
            return true;
        }

        Player player = (Player) commandSender;

        String name = player.getName(); //เอาชื่อ

        // ดึงเวลา
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        // ข้อความ
        player.sendMessage("§bสวัสดี §a" + name + "§f!");
        player.sendMessage("§eเวลา: §7" + formattedDateTime);

        return true;
    }
}
