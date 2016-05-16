package me.ecminer.tos;

import me.ecminer.tos.listeners.GameListener;
import me.ecminer.tos.role.RoleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TOSPlugin extends JavaPlugin {

    private static TOSPlugin instance;

    public static TOSPlugin getInstance() {
        return instance;
    }

    private RoleManager roleManager = new RoleManager();

    public RoleManager getRoleManager() {
        return roleManager != null ? roleManager : (roleManager = new RoleManager());
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new GameListener(), this);
    }
}
