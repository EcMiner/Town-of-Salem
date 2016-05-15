package me.ecminer.tos.role;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Role {

    protected Player player;
    protected final List<RoleAttribute> attributes;
    protected final RoleType identifier;
    protected boolean isDead;

    protected Role(Player player, RoleType identifier) {
        this.player = player;
        this.attributes = new ArrayList<>();
        this.identifier = identifier;
    }

    /**
     * Get whether the player with this role has been killed (caused by lynching, killing, etc.)
     *
     * @return Returns whether the player is dead
     */
    public final boolean isDead() {
        return isDead;
    }

    /**
     * Kills the player and performs any actions that come with a player dying by any cause
     */
    public final void die() {
        if (!isDead) {
            isDead = true;
        }
    }

    /**
     * Tells this role that the player who is using the role has disconnected
     */
    public final void disconnect() {
        player = null;
    }

    /**
     * Checks whether the player with this role is still online
     *
     * @return Returns whether the player is online
     */
    public final boolean isOnline() {
        return player != null;
    }

    /**
     * Get the {@link RoleType unique identifier} of this role
     *
     * @return Returns the UID of the role
     * @see RoleType
     */
    public final RoleType getIdentifier() {
        return identifier;
    }

    /**
     * Get the {@link Player player} who's using this role
     *
     * @return Returns the player who's using the role
     * @see Player
     */
    public final Player getPlayer() {
        return player;
    }

    /**
     * Adds a {@link RoleAttribute attribute} to this role. Adding an attribute will add special behaviour to a role,
     * see {@link RoleAttributes RoleAttributes} for a description of the default attributes
     *
     * @param attribute The attribute you want to add
     * @see RoleAttribute
     * @see RoleAttributes
     */
    protected final void addAttribute(RoleAttribute attribute) {
        attributes.add(attribute);
    }

    /**
     * <p>Checks whether a player with a role is able to kill this player.</p> <p>By default it will return true, if you
     * want to add special behaviour and checks to whether a role can kill another role, override this in a town
     * realization</p>
     *
     * @param role The role of the player who's trying to kill this player
     * @return Returns whether a player can kill this player
     */
    public boolean canGetKilled(Role role) {
        return true;
    }
}
