package me.ecminer.tos.role.ability.abilities;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.ability.NightAbility;

public interface VisitYouAbility extends NightAbility {

    void onVisitYou(Role whom);

}