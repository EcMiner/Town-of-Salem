package me.ecminer.tos.role.target;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.Role;

import java.util.HashMap;
import java.util.Map;

public class TargetManager {

    private Map<Game, Map<Role, Role>> selectedTarget = new HashMap<>();

    public boolean hasTarget(Role role) {
        return role != null && selectedTarget.containsKey(role.getGame()) && selectedTarget.get(role.getGame())
                .containsKey(role);
    }

    public Role getTarget(Role role) {
        return role != null && selectedTarget.containsKey(role.getGame()) ? selectedTarget.get(role.getGame())
                .get(role) : null;
    }

    public void setTarget(Role role, Role target) {
        if ((role != null && target != null) && role.getGame().equals(target.getGame())) {
            if (!selectedTarget.containsKey(role.getGame())) {
                selectedTarget.put(role.getGame(), new HashMap<>());
            }
            selectedTarget.get(role.getGame()).put(role, target);
        }
    }

    public void removeTarget(Role role) {
        if (role != null && selectedTarget.containsKey(role.getGame())) {
            selectedTarget.get(role.getGame()).remove(role);
            if (selectedTarget.get(role.getGame()).size() == 0) {
                selectedTarget.remove(role.getGame());
            }
        }
    }

    public void clearTargets(Game game) {
        selectedTarget.remove(game);
    }

}
