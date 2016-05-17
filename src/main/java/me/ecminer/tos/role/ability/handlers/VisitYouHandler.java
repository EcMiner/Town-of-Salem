package me.ecminer.tos.role.ability.handlers;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleOption;
import me.ecminer.tos.role.ability.AbilityHandler;
import me.ecminer.tos.role.ability.abilities.VisitYouAbility;

import java.util.List;

public class VisitYouHandler extends AbilityHandler<VisitYouAbility> {

    @Override
    public void handle(Role role) {
        if (role instanceof VisitYouAbility) {
            if (role.hasOption(RoleOption.VISITED)) {
                List<Role> rolesVisited = role.getOption(RoleOption.VISITED).asList(Role.class);
                for (Role visited : rolesVisited) {
                    ((VisitYouAbility) role).onVisitYou(visited);
                }
            }
        }
    }
}
