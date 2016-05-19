package me.ecminer.tos.role;

import me.ecminer.tos.game.Game;
import me.ecminer.tos.role.goal.Goal;
import me.ecminer.tos.role.target.TargetFilter;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class Role {

    protected Player player;
    protected final UUID playerId;
    protected final List<RoleAttribute> attributes;
    protected final RoleType identifier;
    protected boolean isDead;
    protected final Game game;
    private final Map<String, RoleOption> options = new HashMap<>();
    private boolean killed;
    private TargetFilter nightTargetFilter;
    private TargetFilter dayTargetFilter;
    private Goal goal;

    protected Role(Player player, Game game, RoleType identifier) {
        this.player = player;
        this.playerId = player.getUniqueId();
        this.game = game;
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
            if (player != null) {
                // TODO Display effect
            }
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

    public final String getName() {
        return game.getName(playerId);
    }

    public Game getGame() {
        return game;
    }

    public final UUID getPlayerId() {
        return playerId;
    }

    /**
     * Adds a {@link RoleAttribute attribute} to this role. Adding an attribute will add special behaviour to a role,
     * see {@link RoleAttributes RoleAttributes} for a description of the default attributes
     *
     * @param attribute The attribute you want to add
     *
     * @see RoleAttribute
     * @see RoleAttributes
     */
    public final void addAttribute(RoleAttribute attribute) {
        attributes.add(attribute);
    }

    public final void removeAttribute(RoleAttribute attribute) {
        attributes.remove(attribute);
    }

    public final boolean hasAttribute(RoleAttribute attribute) {
        return attributes.contains(attribute);
    }

    /**
     * <p>Checks whether a player with a role is able to kill this player.</p> <p>By default it will return true, if you
     * want to add special behaviour and checks to whether a role can kill another role, override this in a town
     * realization</p>
     *
     * @param role The role of the player who's trying to kill this player
     *
     * @return Returns whether a player can kill this player
     */
    public boolean canGetKilled(Role role) {
        return true;
    }

    public Collection<RoleOption> getOptions() {
        return options.values();
    }

    public void setOption(String key, Object value) {
        options.put(key.toLowerCase(), new RoleOption(key, value));
    }

    public boolean hasOption(String key) {
        return options.containsKey(key);
    }

    public RoleOption getOption(String key) {
        return options.get(key.toLowerCase());
    }

    public void removeOption(String key) {
        options.remove(key.toLowerCase());
    }

    public void clearOptions() {
        options.clear();
    }

    public boolean kill(Role killer) {
        addVisit(killer);
        if (!hasAttribute(RoleAttributes.NIGHT_IMMUNE) || killer.hasAttribute(RoleAttributes.IGNORE_NIGHT_IMMUNE)) {
            if (!hasOption(RoleOption.KILLED)) {
                setOption(RoleOption.KILLED, new ArrayList<Role>());
            }
            getOption(RoleOption.KILLED).addToList(killer);
            return (killed = true);
        }
        return false;
    }

    public void addVisit(Role visiter) {
        if (!hasOption(RoleOption.VISITED)) {
            setOption(RoleOption.VISITED, new ArrayList<Role>());
        }
        getOption(RoleOption.VISITED).addToList(visiter);
    }

    /**
     * Returns whether the player is killed during a night
     *
     * @return Whether the player killed during the night
     */
    public boolean isKilled() {
        return killed;
    }

    public TargetFilter getNightTargetFilter() {
        return nightTargetFilter;
    }

    public void setNightTargetFilter(TargetFilter nightTargetFilter) {
        this.nightTargetFilter = nightTargetFilter;
    }

    public TargetFilter getDayTargetFilter() {
        return dayTargetFilter;
    }

    public void setDayTargetFilter(TargetFilter dayTargetFilter) {
        this.dayTargetFilter = dayTargetFilter;
    }

    public boolean roleBlock(Role blocker) {
        addVisit(blocker);
        if (!hasAttribute(RoleAttributes.ROLE_BLOCK_IMMUNE)) {
            if (!hasOption(RoleOption.ROLE_BLOCKED)) {
                setOption(RoleOption.ROLE_BLOCKED, new ArrayList<Role>());
            }
            getOption(RoleOption.ROLE_BLOCKED).addToList(blocker);
        }
        return false;
    }

    public boolean isRoleBlocked() {
        return hasOption(RoleOption.ROLE_BLOCKED);
    }

    public List<Role> getRoleBlockers() {
        return hasOption(RoleOption.ROLE_BLOCKED) ? getOption(RoleOption.ROLE_BLOCKED)
                .asList(Role.class) : new ArrayList<>();
    }

    /**
     * Resets data that might have been added during the day or night, like killers, etc.
     */
    public void reset() {
        killed = false;
        clearOptions();
    }

    public final void leave() {
        setDayTargetFilter(null);
        setNightTargetFilter(null);
        setGoal(null);
        onLeave();
        this.player = null;
        options.clear();
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void onSelectTarget(Role target, boolean isDay) {
    }

    public void onLeave() {
    }
}
