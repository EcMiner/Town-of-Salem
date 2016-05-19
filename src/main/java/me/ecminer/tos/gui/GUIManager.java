package me.ecminer.tos.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GUIManager {

    private Map<UUID, GUI> openGUI = new HashMap<>();

    public void setOpenGUI(Player player, GUI gui) {
        openGUI.put(player.getUniqueId(), gui);
    }

    public boolean hasOpenGUI(Player player) {
        return openGUI.containsKey(player.getUniqueId());
    }

    public GUI getOpenGUI(Player player) {
        return openGUI.get(player.getUniqueId());
    }

    public void closeOpenGUI(Player player) {
        openGUI.remove(player.getUniqueId());
    }

}
