package me.ecminer.tos.role.roles;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleTypes;
import org.bukkit.entity.Player;

public class Vigilante extends Role {

    protected Vigilante(Player player) {
        super(player, RoleTypes.VIGILANTE);
    }
}
