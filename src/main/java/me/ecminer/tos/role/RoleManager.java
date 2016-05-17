package me.ecminer.tos.role;

import me.ecminer.tos.exceptions.InvalidRoleException;
import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.ability.Ability;
import me.ecminer.tos.role.ability.AbilityHandler;
import me.ecminer.tos.role.ability.AbilityPriority;
import me.ecminer.tos.role.ability.NightAbility;
import me.ecminer.tos.role.ability.handlers.DayTargetPlayerHandler;
import me.ecminer.tos.role.ability.handlers.NightTargetPlayerHandler;
import me.ecminer.tos.role.roles.Jailor;
import me.ecminer.tos.role.roles.Vigilante;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleManager {

    private Map<RoleType, Class<? extends Role>> registeredRoles = new HashMap<>();
    private Map<Class<? extends Ability>, AbilityHandler> abilityHandlers = new HashMap<>();
    private List<Integer> priorities = new ArrayList<>();
    private Map<Integer, List<Class<? extends Ability>>> abilityPriorities = new HashMap<>();

    public RoleManager() {
        loadDefaultRoles();
        loadDefaultHandlers();
    }

    private void loadDefaultRoles() {
        registerRole(RoleTypes.VIGILANTE, Vigilante.class);
        registerRole(RoleTypes.JAILOR, Jailor.class);
    }

    private void loadDefaultHandlers() {
        addAbilityHandler(new DayTargetPlayerHandler());
        addAbilityHandler(new NightTargetPlayerHandler());
    }

    public Role createRole(RoleType identifier, Player player) {
        Class<? extends Role> roleClass = registeredRoles.get(identifier);
        if (roleClass != null) {
            try {
                return roleClass.getConstructor(Player.class).newInstance(player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean isRole(RoleType identifier) {
        return registeredRoles.containsKey(identifier);
    }

    public void registerRole(RoleType identifier, Class<? extends Role> roleClass) {
        if (roleClass.getConstructors().length > 0) {
            for (Constructor<?> constructor : roleClass.getConstructors()) {
                if (constructor.getParameterCount() == 1 && constructor.getParameters()[0].getType() == Player.class) {
                    registeredRoles.put(identifier, roleClass);

                    for (Class<?> iface : roleClass.getInterfaces()) {
                        if (Ability.class.isAssignableFrom(iface)) {
                            int priority = 0;

                            for (Method abilityMethod : iface.getDeclaredMethods()) {
                                try {
                                    Method method = roleClass
                                            .getDeclaredMethod(abilityMethod.getName(),
                                                    abilityMethod.getParameterTypes());
                                    if (method != null) {
                                        if (method.getAnnotations().length > 0) {
                                            AbilityPriority prio = method.getAnnotation(AbilityPriority.class);
                                            if (prio != null) {
                                                priority = prio.priority();
                                            }
                                        }
                                    }
                                } catch (NoSuchMethodException e) {
                                    System.err.println("Error while registering role, role class: " + roleClass
                                            .getCanonicalName());
                                }
                            }

                            if (!priorities.contains(priority)) {
                                addPriority(priority);
                            }
                            abilityPriorities.get(priority).add((Class<? extends Ability>) iface);
                        }
                    }

                    return;
                }
            }
        }
        try {
            throw new InvalidRoleException(roleClass);
        } catch (InvalidRoleException e) {
            e.printStackTrace();
        }
    }

    private void addPriority(int priority) {
        abilityPriorities.put(priority, new ArrayList<>());
        for (int index = 0; index < priorities.size(); index++) {
            int prio = priorities.get(index);
            if (priority > prio) {
                priorities.add(index, priority);
                return;
            }
        }
        priorities.add(priority);
    }

    public void addAbilityHandler(AbilityHandler actionHandler) {
        abilityHandlers.put(actionHandler.getAbilityClass(), actionHandler);
    }

    public void executeNightAbilities(Game game) {
        executeAbilities(game, NightAbility.class);
    }

    public void executeDayAbilities(Game game) {
        executeAbilities(game, NightAbility.class);
    }

    public void executeAbilities(Game game, Class<? extends Ability> abilitySuperClass) {
        for (int priority : priorities) {
            if (abilityPriorities.containsKey(priority)) {
                for (Class<? extends Ability> ability : abilityPriorities.get(priority)) {
                    if (abilitySuperClass.isAssignableFrom(ability)) {
                        if (abilityHandlers.containsKey(ability)) {
                            AbilityHandler handler = abilityHandlers.get(ability);
                            for (Role role : game.getAlivePlayers()) {
                                if (!role.isDead()) {
                                    handler.handle(role);
                                }
                            }
                            for (Role role : game.getAlivePlayers()) {
                                if (role.isKilled()) {
                                    role.die();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
