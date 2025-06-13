package org.avo.newtest.Event;

import org.avo.newtest.Newtest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class FlyJoinListener implements Listener {

    private final Newtest plugin;

    public FlyJoinListener(Newtest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        boolean wasFlying = plugin.getDataConfig().getConfig().getBoolean("fly." + uuid,false);
        player.setAllowFlight(wasFlying);

        if (wasFlying) {
            player.sendMessage("§b[INFO] §aบินได้นะ");
        }

    }

}
