package me.ecminer.tos.role.ability.handlers;

import me.ecminer.tos.TOSPlugin;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.ability.AbilityHandler;
import me.ecminer.tos.role.ability.abilities.DayTargetPlayerAbility;

public class DayTargetPlayerHandler extends AbilityHandler<DayTargetPlayerAbility> {

    @Override
    public void handle(Role role) {
        if (role != null && role.getGame().isDay() && role instanceof DayTargetPlayerAbility) {
            Role target = TOSPlugin.getInstance().getTargetManager().getTarget(role);
            if (target != null) {
                ((DayTargetPlayerAbility) role).targetDuringDay(target);
            }
        }
    }
}
