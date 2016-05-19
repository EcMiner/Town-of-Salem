package me.ecminer.tos.role.roles;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleAttributes;
import me.ecminer.tos.role.RoleTypes;
import me.ecminer.tos.role.ability.AbilityPriority;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;
import me.ecminer.tos.role.ability.abilities.VisitYouAbility;
import me.ecminer.tos.role.goal.goals.TownGoal;
import me.ecminer.tos.role.target.filters.TargetSelfFilter;
import org.bukkit.entity.Player;

public class Veteran extends Role implements NightTargetPlayerAbility, VisitYouAbility {

    private int alerts = 3;
    private boolean isOnAlert;

    protected Veteran(Player player, Game game) {
        super(player, game, RoleTypes.VETERAN);
        addAttribute(RoleAttributes.ROLE_BLOCK_IMMUNE);
        addAttribute(RoleAttributes.CONTROL_IMMUNE);
        addAttribute(RoleAttributes.UNIQUE);
        setNightTargetFilter(new TargetSelfFilter(this) {

            @Override
            public boolean canSelectTarget() {
                return alerts > 0;
            }

        });
        setGoal(new TownGoal(this));
    }

    @AbilityPriority(priority = 6)
    public void targetDuringNight(Role target) {
        if (target == this) {
            if (alerts > 0) {
                --alerts;
                isOnAlert = true;
                addAttribute(RoleAttributes.NIGHT_IMMUNE);
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        isOnAlert = false;
        sentMessage = false;
        removeAttribute(RoleAttributes.NIGHT_IMMUNE);
    }

    boolean sentMessage = false;

    @AbilityPriority(priority = 5)
    public void onVisitYou(Role whom) {
        if (isOnAlert) {
            whom.kill(this);
            // TODO Send message to target
            if (!sentMessage) {
                sentMessage = true;
                // TODO Send message to vet that he killed someone
            }
        }
    }

}
