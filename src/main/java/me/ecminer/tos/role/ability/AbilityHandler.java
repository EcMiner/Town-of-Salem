package me.ecminer.tos.role.ability;

import me.ecminer.tos.role.Role;

import java.lang.reflect.ParameterizedType;

public abstract class AbilityHandler<T extends Ability> {

    private Class<T> abilityClass;

    @SuppressWarnings("unchecked")
    public AbilityHandler() {
        this.abilityClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract void handle(Role role);

    public Class<T> getAbilityClass() {
        return abilityClass;
    }
}
