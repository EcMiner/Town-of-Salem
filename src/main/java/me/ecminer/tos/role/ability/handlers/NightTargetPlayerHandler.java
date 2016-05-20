package me.ecminer.tos.role.ability.handlers;

import me.ecminer.tos.TOSPlugin;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.ability.AbilityHandler;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;

public class NightTargetPlayerHandler extends AbilityHandler<NightTargetPlayerAbility> {

    @Override
    public void handle(Role role) {
        if (role != null && role.getGame().isNight() && role instanceof NightTargetPlayerAbility) {
            Role target = TOSPlugin.getInstance().getTargetManager().getTarget(role);
            if (target != null) {
                ((NightTargetPlayerAbility) role).targetDuringNight(target);
            }
        }
    }
}
