package me.ecminer.tos.role.roles;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleAttributes;
import me.ecminer.tos.role.RoleTeam;
import me.ecminer.tos.role.RoleTypes;
import me.ecminer.tos.role.ability.AbilityPriority;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;
import me.ecminer.tos.role.goal.Goal;
import org.bukkit.entity.Player;

public class SerialKiller extends Role implements NightTargetPlayerAbility {

    protected SerialKiller(Player player) {
        super(player, RoleTypes.SERIAL_KILLER);
        addAttribute(RoleAttributes.NIGHT_IMMUNE);
        setGoal(new Goal(this) {

            @Override
            public boolean hasReachedGoal(Game game) {
                for (Role alive : game.getAlivePlayers()) {
                    if (alive.getIdentifier().getTeam() == RoleTeam.TOWN
                            || alive.getIdentifier().getTeam() == RoleTeam.MAFIA) {
                        return false;
                    }
                }
                return true;
            }
        });
    }

    private boolean sentMessage;

    @AbilityPriority(priority = 1)
    public void targetDuringNight(Role target) {
        if (isRoleBlocked()) {
            for (Role blocker : getRoleBlockers()) {
                blocker.kill(this);
                // TODO Send message to blocker
                if (!sentMessage) {
                    // TODO Send message to SK
                    sentMessage = true;
                }
            }
            return;
        }
        target.kill(this);
    }

    @Override
    public void reset() {
        super.reset();
        sentMessage = false;
    }
}
