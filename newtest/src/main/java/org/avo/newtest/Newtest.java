package org.avo.newtest;

import org.avo.newtest.Command.*;
import org.avo.newtest.Event.FlyJoinListener;
import org.avo.newtest.Event.GuiSave;
import org.avo.newtest.TabCompleter.AvonewTabCompleter;
import org.avo.newtest.config.DataConfig;
import org.bukkit.plugin.java.JavaPlugin;



public final class Newtest extends JavaPlugin {

    //เก็บขอนฟิก
    private DataConfig dataConfig;

    @Override
    public void onEnable() {

        //โหลดไฟล์
        dataConfig = new DataConfig(this);

        //ลงทะเบียนคำสั่ง
        HelloCommand helloCommand = new HelloCommand();
        this.getCommand("hello").setExecutor(helloCommand);

        FlyCommand commandExecutor = new FlyCommand(this);
        this.getCommand("fly").setExecutor(commandExecutor);
        this.getCommand("feed").setExecutor(commandExecutor);
        this.getCommand("havo").setExecutor(commandExecutor);

        //avonew
        AvonewCommand avonewCommand = new AvonewCommand(this);
        this.getCommand("avonew").setExecutor(avonewCommand);

        //gui
        GuiCommand guiCommand = new GuiCommand(this);

        //event gui
        getServer().getPluginManager().registerEvents(new GuiSave(guiCommand), this);

        //tab completer
        AvonewTabCompleter avonewTabCompleter = new AvonewTabCompleter();
        this .getCommand("avonew").setTabCompleter(avonewTabCompleter);


        FlyJoinListener flyJoinListener = new FlyJoinListener(this);
        getServer().getPluginManager().registerEvents(flyJoinListener, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DataConfig getDataConfig() {
        return dataConfig;
    }
}
