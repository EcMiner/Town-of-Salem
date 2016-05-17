package me.ecminer.tos.role.goal.goals;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleTeam;
import me.ecminer.tos.role.RoleTypes;
import me.ecminer.tos.role.goal.Goal;

public class MafiaGoal extends Goal {

    public MafiaGoal(Role role) {
        super(role);
    }

    @Override
    public boolean hasReachedGoal(Game game) {
        for (Role alive : game.getAlivePlayers()) {
            if (alive.getIdentifier().getTeam() == RoleTeam.TOWN || alive.getIdentifier() == RoleTypes.SERIAL_KILLER) {
                return false;
            }
        }
        return true;
    }
}
