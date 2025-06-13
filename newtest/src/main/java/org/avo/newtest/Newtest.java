package org.avo.newtest;

import org.avo.newtest.Command.*;
import org.avo.newtest.Event.FlyJoinListener;
import org.avo.newtest.Event.GuiSave;
import org.avo.newtest.Event.InvSave;
import org.avo.newtest.TabCompleter.AvonewTabCompleter;
import org.avo.newtest.config.DataConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class Newtest extends JavaPlugin {

    private DataConfig dataConfig;

    @Override
    public void onEnable() {

        // โหลด config
        dataConfig = new DataConfig(this);

        // คำสั่งตรง
        HelloCommand helloCommand = new HelloCommand();
        this.getCommand("hello").setExecutor(helloCommand);

        FlyCommand flyCommand = new FlyCommand(this);
        this.getCommand("fly").setExecutor(flyCommand);
        this.getCommand("feed").setExecutor(flyCommand);
        this.getCommand("havo").setExecutor(flyCommand);

        // เตรียมคำสั่งย่อย
        GuiCommand guiCommand = new GuiCommand(this);
        InvCommand invCommand = new InvCommand(this); // สำคัญมาก

        // คำสั่งหลัก /avonew
        AvonewCommand avonewCommand = new AvonewCommand(guiCommand, invCommand, this);
        this.getCommand("avonew").setExecutor(avonewCommand);
        this.getCommand("avonew").setTabCompleter(new AvonewTabCompleter());

        // ลงทะเบียนอีเวนต์
        getServer().getPluginManager().registerEvents(new FlyJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new GuiSave(guiCommand), this);
        getServer().getPluginManager().registerEvents(new InvSave(invCommand.getInvConfig()), this); // ใช้ config จาก invCommand
    }

    @Override
    public void onDisable() {
        // ปิด plugin
    }

    public DataConfig getDataConfig() {
        return dataConfig;
    }
}
