package me.ecminer.tos.game;

import me.ecminer.tos.lynch.Lynch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Game {

    private Map<UUID, List<UUID>> killedBy = new HashMap<>();
    private Map<UUID, List<UUID>> killedPlayers = new HashMap<>();
    private Map<UUID, Lynch> lynches = new HashMap<>();

}
