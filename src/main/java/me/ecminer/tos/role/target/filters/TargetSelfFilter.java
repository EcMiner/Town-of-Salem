package me.ecminer.tos.role.target.filters;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.target.TargetFilter;

public class TargetSelfFilter extends TargetFilter {

    protected final Role role;

    public TargetSelfFilter(Role role) {
        this.role = role;
    }


    @Override
    public boolean canTarget(Role target) {
        return target == role;
    }

    @Override
    public boolean canSelectTarget() {
        return true;
    }
}
