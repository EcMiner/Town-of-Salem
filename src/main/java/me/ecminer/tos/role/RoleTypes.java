package me.ecminer.tos.role;

import me.ecminer.tos.TOSPlugin;
import me.ecminer.tos.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum RoleTypes implements RoleType {

    VIGILANTE("Vigilante", ChatColor.GREEN, RoleCategories.KILLING, RoleTeam.TOWN),
    JAILOR("Jailor", ChatColor.GREEN, RoleCategories.KILLING, RoleTeam.TOWN),
    VETERAN("Veteran", ChatColor.GREEN, RoleCategories.KILLING, RoleTeam.TOWN),
    SERIAL_KILLER("Serial Killer", ChatColor.DARK_BLUE, RoleCategories.KILLING, RoleTeam.NEUTRAL);

    private final String name;
    private final ChatColor nameColor;
    private final RoleCategory category;
    private final RoleTeam team;

    RoleTypes(String name, ChatColor nameColor, RoleCategory category, RoleTeam team) {
        this.name = name;
        this.nameColor = nameColor;
        this.category = category;
        this.team = team;
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

    @Override
    public RoleTeam getTeam() {
        return team;
    }

    public Role createRole(Player player, Game game) {
        return TOSPlugin.getInstance().getRoleManager().createRole(this, player, game);
    }

}
