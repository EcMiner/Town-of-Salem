package me.ecminer.tos.configuration;

import me.ecminer.tos.TOSPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {

    private int discussionTime = 45;
    private int voteTime = 30;
    private int defenseTime = 20;
    private int judgementTime = 20;
    private int finalWordsTime = 5;
    private int nightTime = 40;

    public Configuration() {
        load();
    }

    public void load() {
        FileConfiguration config = TOSPlugin.getInstance().getConfig();

        this.discussionTime = config.getInt("discussion-time", discussionTime);
        this.voteTime = config.getInt("vote-time", voteTime);
        this.defenseTime = config.getInt("defense-time", defenseTime);
        this.judgementTime = config.getInt("judgement-time", judgementTime);
        this.finalWordsTime = config.getInt("final-words-time", finalWordsTime);
        this.nightTime = config.getInt("night-time", nightTime);
    }

    public int getDiscussionTime() {
        return discussionTime;
    }

    public int getVoteTime() {
        return voteTime;
    }

    public int getDefenseTime() {
        return defenseTime;
    }

    public int getJudgementTime() {
        return judgementTime;
    }

    public int getFinalWordsTime() {
        return finalWordsTime;
    }

    public int getNightTime() {
        return nightTime;
    }
}
