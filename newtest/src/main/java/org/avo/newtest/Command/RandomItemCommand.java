package org.avo.newtest.Command;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomItemCommand implements CommandExecutor {

    private final Random random = new Random();

    private final List<Material> customItems = Arrays.asList(
        Material.DIAMOND,
        Material.GOLD_INGOT,
        Material.IRON_INGOT,
        Material.EMERALD,
        Material.NETHERITE_INGOT
    );

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cคำสั่งนี้ใช้ได้เฉพาะผู้เล่นเท่านั้น");
            return true;
        }

        Player player = (Player) sender;

        int amount = 1;

        if (args.length >= 2) {
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cใส่ตังเลข");
                return true;
            }
        }

        for (int i = 0; i < amount; i++) {
            dropRandomItem(player);
        }

        player.sendMessage("สุ่มไอเท่มฉลาม " + amount + " ชิ้น");
        return true;
    }

    private void dropRandomItem(Player player) {
        Material randomMaterial = customItems.get(random.nextInt(customItems.size()));
        ItemStack item = new ItemStack(randomMaterial);

        Location loc = player.getLocation().add (randomOffset(), 1, randomOffset());
        player.getWorld().dropItemNaturally(loc, item);
    }

    private double randomOffset() {
        return (random.nextDouble() - 0.5) * 2; // สุ่มระยะห่างระหว่าง -1 ถึง 1
    }
}
