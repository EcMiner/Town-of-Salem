package me.ecminer.tos.role;

import org.bukkit.entity.Player;

public abstract class Role {

    protected Player player;
    protected final RoleType identifier;
    protected final RoleTeam team;
    protected boolean isDead;

    protected Role(Player player, RoleType identifier, RoleTeam team) {
        this.player = player;
        this.identifier = identifier;
        this.team = team;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public final void disconnect() {
        player = null;
    }

    public final boolean isOnline() {
        return player != null;
    }

    public RoleType getIdentifier() {
        return identifier;
    }

    public RoleTeam getTeam() {
        return team;
    }

    public final Player getPlayer() {
        return player;
    }
}
