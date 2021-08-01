/*
 * //Plugin made by JonasBro
 * //Do not Copy it in any way
 */

/*
 * //Plugin made by JonasBro
 * //Do not Copy it in any way
 */


package de.jonasbro.timevoter;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main extends JavaPlugin {

    public String pr = ChatColor.GOLD + "[VoteTimer] " + ChatColor.WHITE;

    private Set<UUID> noVote = new HashSet<>();
    private Set<UUID> yesVote= new HashSet<>();

    public int timeToVote;
    public boolean isVoteActive;
    public int voteDelay;
    public Instant lastVote;
    public VoteUtil voteUtil;

    @Override
    public void onEnable() {
        System.out.println(pr + "Enabling...");

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        timeToVote = this.getConfig().getInt("time-to-vote");
        voteDelay = this.getConfig().getInt("vote-delay");

        voteUtil = new VoteUtil(this);

        this.getServer().getPluginManager().registerEvents(new VoteEvent(this), this);
        this.getCommand("timevote").setExecutor(new TimeVoteCommand(this));
    }

    public Set<UUID> getNoVote() {
        return noVote;
    }

    public Set<UUID> getYesVote() {
        return yesVote;
    }

    @Override
    public void onDisable() {
        noVote.clear();
        yesVote.clear();


    }
}
