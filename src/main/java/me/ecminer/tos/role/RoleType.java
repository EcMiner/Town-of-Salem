package me.ecminer.tos.role;

import org.bukkit.ChatColor;

public interface RoleType {

    String getName();

    ChatColor getNameColor();

    RoleCategory getCategory();

    RoleTeam getTeam();

}
