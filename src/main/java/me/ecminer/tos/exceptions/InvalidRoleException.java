package me.ecminer.tos.exceptions;

import me.ecminer.tos.role.Role;

public class InvalidRoleException extends Exception {

    public InvalidRoleException(Class<? extends Role> roleClass) {
        super("Invalid role! The class " + roleClass.getCanonicalName() + " doesn't have a constructor with only a Player as parameter!");
    }
}
