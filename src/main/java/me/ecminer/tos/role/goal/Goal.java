package me.ecminer.tos.role.goal;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.Role;

public abstract class Goal {

    protected final Role role;

    public Goal(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public abstract boolean hasReachedGoal(Game game);
}
