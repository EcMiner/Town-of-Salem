package me.ecminer.tos.role.ability.abilities;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.ability.DayAbility;

public interface DayTargetPlayerAbility extends DayAbility {

    void targetDuringDay(Role target);

}
