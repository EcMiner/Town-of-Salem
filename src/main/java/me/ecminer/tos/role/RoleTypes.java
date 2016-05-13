package me.ecminer.tos.role;

import me.ecminer.tos.TOSPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum RoleTypes implements RoleType {

    VIGILANTE("Vigilante", ChatColor.GREEN, RoleCategories.TOWN_KILLING);

    private final String name;
    private final ChatColor nameColor;
    private final RoleCategory category;

    RoleTypes(String name, ChatColor nameColor, RoleCategory category) {
        this.name = name;
        this.nameColor = nameColor;
        this.category = category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ChatColor getNameColor() {
        return nameColor;
    }

    @Override
    public RoleCategory getCategory() {
        return category;
    }

    public Role createRole(Player player) {
        return TOSPlugin.getInstance().getRoleManager().createRole(this, player);
    }

}
