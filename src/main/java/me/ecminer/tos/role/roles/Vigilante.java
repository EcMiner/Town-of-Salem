package me.ecminer.tos.role.roles;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.RoleTypes;
import me.ecminer.tos.role.ability.AbilityPriority;
import me.ecminer.tos.role.ability.abilities.NightTargetPlayerAbility;
import me.ecminer.tos.role.goal.goals.TownGoal;
import org.bukkit.entity.Player;

public class Vigilante extends Role implements NightTargetPlayerAbility {

    protected Vigilante(Player player, Game game) {
        super(player, game, RoleTypes.VIGILANTE);
        setGoal(new TownGoal(this));
    }

    @AbilityPriority(priority = 1)
    public void targetDuringNight(Role target) {
        target.kill(this);
    }
}
