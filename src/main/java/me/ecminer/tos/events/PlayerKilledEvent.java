package me.ecminer.tos.events;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.Role;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerKilledEvent extends Event {

    public static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private final Game game;
    private final Role killer;
    private final Role killed;

    public PlayerKilledEvent(Game game, Role killer, Role killed) {
        this.game = game;
        this.killer = killer;
        this.killed = killed;
    }

    public Game getGame() {
        return game;
    }

    public Role getKilled() {
        return killed;
    }

    public Role getKiller() {
        return killer;
    }
}
