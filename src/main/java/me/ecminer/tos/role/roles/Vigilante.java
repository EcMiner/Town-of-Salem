package me.ecminer.tos.role.roles;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleTypes;
import me.ecminer.tos.role.ability.AbilityPriority;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;
import org.bukkit.entity.Player;

public class Vigilante extends Role implements NightTargetPlayerAbility {

    protected Vigilante(Player player) {
        super(player, RoleTypes.VIGILANTE);
    }

    @AbilityPriority(priority = 1)
    public void targetDuringNight(Role target) {
    }
}
