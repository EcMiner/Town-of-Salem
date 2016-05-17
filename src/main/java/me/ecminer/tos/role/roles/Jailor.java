package me.ecminer.tos.role.roles;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleAttributes;
import me.ecminer.tos.role.RoleTeam;
import me.ecminer.tos.role.RoleTypes;
import me.ecminer.tos.role.ability.AbilityPriority;
import me.ecminer.tos.role.ability.abilities.DayTargetPlayerAbility;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;
import me.ecminer.tos.role.goal.goals.TownGoal;
import me.ecminer.tos.role.target.TargetFilter;
import me.ecminer.tos.role.target.filters.TargetAllButSelfFilter;
import org.bukkit.entity.Player;

public class Jailor extends Role implements DayTargetPlayerAbility, NightTargetPlayerAbility {

    private Role jailed;
    private int executions = 3;

    protected Jailor(Player player) {
        super(player, RoleTypes.JAILOR);
        addAttribute(RoleAttributes.IGNORE_NIGHT_IMMUNE);
        setDayTargetFilter(new TargetAllButSelfFilter(this));
        setNightTargetFilter(new TargetFilter() {

            @Override
            public boolean canTarget(Role target) {
                return target == jailed;
            }

            @Override
            public boolean canSelectTarget() {
                return jailed != null && executions > 0;
            }
        });
        setGoal(new TownGoal(this));
    }

    @Override
    public void targetDuringDay(Role target) {
        this.jailed = target;
    }

    @AbilityPriority(priority = 3)
    public void targetDuringNight(Role target) {
        if (executions > 0) {
            executions--;
            if (isRoleBlocked() && target instanceof SerialKiller) {
                // TODO Make the SK kill the Jailor
                return;
            } else if (isRoleBlocked()) {
                // TODO Send roleblock message
                return;
            }
            target.kill(this);
            if (target.getIdentifier().getTeam() == RoleTeam.MAFIA) {
                executions = 0;
            }
            // TODO Send message to target
        }
    }

    @Override
    public void reset() {
        super.reset();
        jailed = null;
    }
}
