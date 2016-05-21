package me.ecminer.tos.role.target;

import me.ecminer.tos.role.Role;

public abstract class TargetFilter {

    public abstract boolean canTarget(Role target);

    public abstract boolean canSelectTarget();

}
