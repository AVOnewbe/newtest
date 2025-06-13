package org.avo.newtest.Command;

import org.avo.newtest.Newtest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FlyCommand implements CommandExecutor {

    private final HashMap<UUID, Long> feedCooldown = new HashMap<>();
    private final Newtest plugin;

    public FlyCommand(Newtest plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("ของผู้เล่น");
            return true;
        }
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("fly")) {
            boolean avofly = player.getAllowFlight();
            boolean newStatus = !avofly;

            player.setAllowFlight(newStatus);
            player.sendMessage("§aบิน: " + (avofly ? "§cปิด" : "§aเปิด"));
            plugin.getDataConfig().getConfig().set("fly." + player.getUniqueId(), newStatus);
            plugin.getDataConfig().saveConfig();

            return true;
        }

        if (command.getName().equalsIgnoreCase("feed")) {
            UUID uuid = player.getUniqueId();
            long now = System.currentTimeMillis();

            if (feedCooldown.containsKey(uuid)) {
                long lastUse = feedCooldown.get(uuid);
                long secondsleft = 10 - ((now - lastUse) / 1000);
                if (secondsleft > 0) {
                    player.sendMessage("§cรอ " + secondsleft + " วิ");
                    return true;
                }
            }

            player.setFoodLevel(2);
            player.setSaturation(1);
            player.sendMessage("อิ่มจังตังอยู่ครบ");
            feedCooldown.put(uuid, now);
            return true;
        }

        if (command.getName().equalsIgnoreCase("havo")) {
            if (args.length == 0) {
                heal(player);
                player.sendMessage("§dผู้กล้าสายฮิล");
                return true;
            } else {
                if (!player.hasPermission("newtest.heal")) {
                    player.sendMessage("§cเองไม่ใช้ผู้กล้า");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if (target == null || !target.isOnline()) {
                    player.sendMessage("§cไม่มีคนให้ฮิล " + args[0]);
                    return true;
                }
                heal(target);
                target.sendMessage("§dได้รับฮิลจากผู้กล้า " + player.getName());
                player.sendMessage("§aฮิลให้ " + target.getName() + "แล้ว");
                return true;
            }
        }
        return true;
    }

    private void heal(Player target) {
        target.setHealth(target.getMaxHealth());
        target.setFireTicks(0);
        target.getActivePotionEffects().forEach(potionEffect -> target.removePotionEffect(potionEffect.getType()));
    }
}