package me.ecminer.tos.role;

import me.ecminer.tos.exceptions.InvalidRoleException;
import me.ecminer.tos.role.roles.Vigilante;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class RoleManager {

    private Map<RoleType, Class<? extends Role>> registeredRoles = new HashMap<>();

    public RoleManager() {
        loadDefaults();
    }

    private void loadDefaults() {
        registerRole(RoleTypes.VIGILANTE, Vigilante.class);
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
}
