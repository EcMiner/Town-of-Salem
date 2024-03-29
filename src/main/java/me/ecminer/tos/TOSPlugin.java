package me.ecminer.tos;

import me.ecminer.tos.configuration.Configuration;
import me.ecminer.tos.game.GameManager;
import me.ecminer.tos.gui.GUIManager;
import me.ecminer.tos.listeners.GUIListener;
import me.ecminer.tos.listeners.GameListener;
import me.ecminer.tos.role.RoleManager;
import me.ecminer.tos.role.target.TargetManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TOSPlugin extends JavaPlugin {

    private static TOSPlugin instance;

    public static TOSPlugin getInstance() {
        return instance;
    }

    private RoleManager roleManager = new RoleManager();
    private GUIManager guiManager = new GUIManager();
    private GameManager gameManager = new GameManager();
    private TargetManager targetManager = new TargetManager();
    private Configuration configuration = new Configuration();

    public RoleManager getRoleManager() {
        return roleManager != null ? roleManager : (roleManager = new RoleManager());
    }

    public GUIManager getGuiManager() {
        return guiManager != null ? guiManager : (guiManager = new GUIManager());
    }

    public GameManager getGameManager() {
        return gameManager != null ? gameManager : (gameManager = new GameManager());
    }

    public TargetManager getTargetManager() {
        return targetManager != null ? targetManager : (targetManager = new TargetManager());
    }

    public Configuration getConfiguration() {
        return configuration != null ? configuration : (configuration = new Configuration());
    }

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new GameListener(), this);
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
    }
}
