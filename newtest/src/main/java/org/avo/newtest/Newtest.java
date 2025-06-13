package org.avo.newtest;

import org.avo.newtest.Command.*;
import org.avo.newtest.Event.FlyJoinListener;
import org.avo.newtest.Event.GuiSave;
import org.avo.newtest.Event.InvSave;
import org.avo.newtest.TabCompleter.AvonewTabCompleter;
import org.avo.newtest.config.DataConfig;
import org.avo.newtest.config.InvConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class Newtest extends JavaPlugin {

    private DataConfig dataConfig;

    @Override
    public void onEnable() {
        // โหลดไฟล์ config
        dataConfig = new DataConfig(this);

        //
        HelloCommand helloCommand = new HelloCommand();
        FlyCommand flyCommand = new FlyCommand(this);

        getCommand("hello").setExecutor(helloCommand);
        getCommand("fly").setExecutor(flyCommand);
        getCommand("feed").setExecutor(flyCommand);
        getCommand("havo").setExecutor(flyCommand);

        // คำสั่งรวม /avonew
        GuiCommand guiCommand = new GuiCommand(this);
        AvonewCommand avonewCommand = new AvonewCommand(guiCommand, this);

        getCommand("avonew").setExecutor(avonewCommand);
        getCommand("avonew").setTabCompleter(new AvonewTabCompleter());

        // ลงทะเบียน event
        getServer().getPluginManager().registerEvents(new FlyJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new GuiSave(guiCommand), this);
        getServer().getPluginManager().registerEvents(new InvSave(new InvConfig(this)), this);
    }

    @Override
    public void onDisable() {
        // ปิด plugin
    }

    public DataConfig getDataConfig() {
        return dataConfig;
    }
}
