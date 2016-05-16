package me.ecminer.tos.role.roles;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleTypes;
import me.ecminer.tos.role.ability.abilities.DayTargetPlayerAbility;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;
import org.bukkit.entity.Player;

public class Jailor extends Role implements DayTargetPlayerAbility, NightTargetPlayerAbility {

    protected Jailor(Player player) {
        super(player, RoleTypes.JAILOR);
    }

    @Override
    public void targetDuringDay(Role target) {

    }

    @Override
    public void targetDuringNight(Role target) {

    }
}
