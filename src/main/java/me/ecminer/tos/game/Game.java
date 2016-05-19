package me.ecminer.tos.game;

import me.ecminer.tos.lynch.Lynch;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleType;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    private Map<UUID, List<UUID>> killedBy = new HashMap<>();
    private Map<UUID, List<UUID>> killedPlayers = new HashMap<>();
    private Map<UUID, List<Lynch>> lynches = new HashMap<>();
    private Map<UUID, String> playerNames = new HashMap<>();
    private Map<String, Role> nameRoles = new HashMap<>();

    public List<Role> getAlivePlayers() {
        return null;
    }

    public boolean isDay() {
        return true;
    }

    public Role getRole(Player player) {
        return getRole(player.getUniqueId());
    }

    public Role getRole(UUID playerId) {
        return nameRoles.get(playerNames.get(playerId));
    }

    public Role getRole(String name) {
        return nameRoles.get(name);
    }

    public boolean isInGame(Player player) {
        return isInGame(player.getUniqueId());
    }

    public boolean isInGame(UUID playerId) {
        return playerNames.containsKey(playerId) && nameRoles.containsKey(playerNames.get(playerId));
    }

    public String getName(Player player) {
        return getName(player.getUniqueId());
    }

    public String getName(UUID playerId) {
        return playerNames.get(playerId);
    }

    public List<Lynch> getLynches(Player player) {
        return getLynches(player.getUniqueId());
    }

    public List<Lynch> getLynches(UUID playerId) {
        return lynches.getOrDefault(playerId, new ArrayList<>());
    }

}
