package me.ecminer.tos.game;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {

    private Map<UUID, Game> inGame = new HashMap<>();

    public boolean isInGame(Player player) {
        return inGame.containsKey(player.getUniqueId());
    }

    public Game getGame(Player player) {
        return inGame.get(player.getUniqueId());
    }

    public void removeInGame(Player player) {
        inGame.remove(player.getUniqueId());
    }

    public void setInGame(Player player, Game game) {
        inGame.put(player.getUniqueId(), game);
    }

}
