package me.ecminer.tos.events;

import me.ecminer.tos.game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TimeChangeEvent extends Event {

    public static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    private final Game game;
    private final boolean night;

    public TimeChangeEvent(Game game, boolean night) {
        this.game = game;
        this.night = night;
    }

    public Game getGame() {
        return game;
    }

    public boolean isNight() {
        return night;
    }
}
