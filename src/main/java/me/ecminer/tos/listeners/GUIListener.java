package me.ecminer.tos.listeners;

import me.ecminer.tos.TOSPlugin;
import me.ecminer.tos.game.Game;
import me.ecminer.tos.gui.GUI;
import me.ecminer.tos.gui.GUIManager;
import me.ecminer.tos.gui.guis.TargetGUI;
import me.ecminer.tos.role.Role;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GUIListener implements Listener {

    private final GUIManager guiManager = TOSPlugin.getInstance().getGuiManager();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent evt) {
        if (evt.getWhoClicked() instanceof Player) {
            Player player = (Player) evt.getWhoClicked();
            if (guiManager.hasOpenGUI(player)) {
                GUI open = guiManager.getOpenGUI(player);
                if (open.isGUI(evt.getInventory())) {
                    if (evt.getRawSlot() > 0 && evt.getRawSlot() < evt.getInventory().getSize()) {
                        evt.setCancelled(open.onClick(player, evt.getRawSlot(), evt.getCurrentItem(), evt.getClick(),
                                evt.getSlotType(),
                                evt.getAction()));
                    }
                } else {
                    guiManager.closeOpenGUI(player);
                    open.onClose(player);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent evt) {
        if (evt.getPlayer() instanceof Player) {
            Player player = (Player) evt.getPlayer();
            if (guiManager.hasOpenGUI(player)) {
                guiManager.getOpenGUI(player).onClose(player);
                guiManager.closeOpenGUI(player);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        Player player = evt.getPlayer();
        if (TOSPlugin.getInstance().getGameManager().isInGame(player)) {
            Game game = TOSPlugin.getInstance().getGameManager().getGame(player);
            if (game.isInGame(player)) {
                for (Role alive : game.getAlivePlayers()) {
                    if (!alive.getPlayerId().equals(player.getUniqueId()) && alive.isOnline()) {
                        if (guiManager.hasOpenGUI(alive.getPlayer()) && guiManager
                                .getOpenGUI(alive.getPlayer()) instanceof TargetGUI) {
                            ((TargetGUI) guiManager.getOpenGUI(alive.getPlayer())).onPlayerLeave(game.getRole(player));
                        }
                    }
                }
            }
        }
    }
}
