package me.ecminer.tos.role.target.filters;

import me.ecminer.tos.role.Role;
import me.ecminer.tos.role.target.TargetFilter;

public class TargetEveryoneFilter extends TargetFilter {

    @Override
    public boolean canTarget(Role target) {
        return true;
    }

    @Override
    public boolean canSelectTarget() {
        return true;
    }
}
