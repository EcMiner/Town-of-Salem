package me.ecminer.tos.listeners;

import me.ecminer.tos.TOSPlugin;
import me.ecminer.tos.game.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {

    private final GameManager gameManager = TOSPlugin.getInstance().getGameManager();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evt) {
        Player player = evt.getPlayer();
        if (gameManager.isInGame(player)) {
            gameManager.getGame(player).getRole(player).leave();
        }
    }

}
