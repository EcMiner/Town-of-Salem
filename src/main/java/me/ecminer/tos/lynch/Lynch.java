package me.ecminer.tos.lynch;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Lynch {

    private final UUID lynchedPlayer;
    private final Map<UUID, LynchVote> votes = new HashMap<>();

    public Lynch(UUID lynchedPlayer) {
        this.lynchedPlayer = lynchedPlayer;
    }

    public UUID getLynchedPlayer() {
        return lynchedPlayer;
    }

    public Map<UUID, LynchVote> getVotes() {
        return votes;
    }

    public LynchVote getVote(UUID player) {
        return votes.getOrDefault(player, LynchVote.ABSTAINED);
    }

    public void setVote(UUID player, LynchVote vote) {
        if (vote == LynchVote.ABSTAINED) {
            removeVote(player);
        } else {
            votes.put(player, vote);
        }
    }

    public void removeVote(UUID player) {
        votes.remove(player);
    }
}
