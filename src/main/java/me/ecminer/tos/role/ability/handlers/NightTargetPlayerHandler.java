package me.ecminer.tos.role.ability.handlers;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.ability.AbilityHandler;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;

public class NightTargetPlayerHandler extends AbilityHandler<NightTargetPlayerAbility> {

    @Override
    public void handle(Role role) {
        if (role instanceof NightTargetPlayerAbility) {
            // TODO Get selected target
        }
    }
}
