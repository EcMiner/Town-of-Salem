package me.ecminer.tos.role.ability;

public abstract class ActionHandler {

    private final int priority;

    public ActionHandler(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
