package sa.heroz.blockpartypls.scoreboard;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.util.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GameState;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;

@SuppressWarnings("deprecation")
public class GameScoreboard {
    private final GamePlayer player;

    private Scoreboard sb;

    public GameScoreboard(final GamePlayer player) {
        this.player = player;
        Bukkit.getScheduler().scheduleSyncDelayedTask(
                iHDeveloperAPI.getPlugin(),
                () -> {
                    ScoreboardManager sbm = Bukkit.getScoreboardManager();
                    GameScoreboard.this.sb = sbm.getNewScoreboard();
                    GameScoreboard.this.update();
                    player.getPlayer().getPlayer().setScoreboard(GameScoreboard.this.sb);
                });
    }

    public Scoreboard getScoreboard() {
        return this.sb;
    }

    public GamePlayer getGamePlayer() {
        return this.player;
    }

    public void update() {
        if (this.sb == null) {
            return;
        }

        Team npcs = getTeam(getScoreboard(), "znpcs");
        npcs.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        npcs.setPrefix(iHDeveloperAPI.color("&8[NPC] "));
        npcs.addEntry("Author");
        Team spectators = getTeam(this.sb, "spectators");
        spectators.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
        spectators.setCanSeeFriendlyInvisibles(true);
        spectators.setPrefix(iHDeveloperAPI.color("&c&lDEAD &8|&f "));
        Team players = getTeam(getScoreboard(), "!players");
        players.setPrefix(iHDeveloperAPI.color("&9&lHERO &8|&f "));
        Team winner = getTeam(getScoreboard(), "!!!winner");
        winner.setPrefix(iHDeveloperAPI.color("&aWINNER &8|&f "));
        Team losers = getTeam(getScoreboard(), "losers");
        losers.setPrefix(iHDeveloperAPI.color("&cLoser &8|&f "));
        for (GamePlayer gamePlayer : Game.getAllPlayers()) {
            spectators.removePlayer(gamePlayer.getPlayer().getPlayer());
            players.removePlayer(gamePlayer.getPlayer().getPlayer());
        }
        if (!Game.getState().equals(GameState.FINISH)) {
            for (GamePlayer gamePlayer : Game.getSpectatorsPlayers())
                spectators.addPlayer(gamePlayer.getPlayer().getPlayer());
            for (GamePlayer p : Game.getAlivePlayers())
                players.addPlayer(p.getPlayer().getPlayer());
        } else {
            winner.addPlayer(Game.getWinner().getPlayer().getPlayer());
            for (GamePlayer loser : Game.getAllPlayers()) {
                if (!loser.getPlayer().getUUID().equals(Game.getWinner().getPlayer().getUUID()))
                    losers.addPlayer(loser.getPlayer().getPlayer());
            }
        }
        Objective o = this.sb.getObjective("sidebar");
        if (o != null)
            o.unregister();
        o = this.sb.registerNewObjective("sidebar", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(iHDeveloperAPI.color("&6&lBLOCKPARTY+"));
        if (Game.getState().equals(GameState.WAITING)) {
            getScore(o, 6, "&6");
            getScore(o, 5, "&eWaiting...");
            getScore(o, 4, "&1");
            getScore(o, 3, "&ePlayers: &a%s&e/&c%s", Game.getAlivePlayers().size(), Settings.maxPlayers);
            getScore(o, 2, "&9");
            getScore(o, 2, "&eVersion &8» &f0.2");
        } else if (Game.getState().equals(GameState.STARTING)) {
            getScore(o, 6, "&6");
            try {
                getScore(o, 5, "&eStarting in %s", (Game.getTimeToStart() / TimeUtils.SECONDS));
            } catch (NullPointerException ex) {
                getScore(o, 5, "&eStarting...");
            }
            getScore(o, 4, "&1");
            getScore(o, 3, "&ePlayers: &a%s&e/&c%s", Game.getAlivePlayers().size(), Settings.maxPlayers);
            getScore(o, 2, "&9");
        } else if (Game.getState().equals(GameState.IN_GAME)) {
            getScore(o, 9, "&6");
            getScore(o, 8, "&ePlayers: &f%s", Game.getAlivePlayers().size());
            getScore(o, 7, "&eSpectators: &f%s", Game.getSpectatorsPlayers().size());
            getScore(o, 6, "&1");
            try {
                getScore(o, 6, "&eRound: &f%s", Game.getCurrentRound().getId());
                getScore(o, 5, "&eMode: &f%s", Game.getCurrentRound().getName());
            } catch (NullPointerException ex) {
                getScore(o, 6, "&eRound: &f&kRRR");
                getScore(o, 5, "&eMode: &f&kRRR");
            }
            getScore(o, 5, "&e&b");
            getScore(o, 4, "&eKills &8» &f%s", getGamePlayer().getKills());
            getScore(o, 3, "&ePoints &8» &f%s", getGamePlayer().getPoints());
        } else if (Game.getState().equals(GameState.FINISH)) {
            getScore(o, 9, "&eGame: &fThe End");
            getScore(o, 8, "&1");
            getScore(o, 7, "&eWinner &8»");
            getScore(o, 7, "&f%s", Game.getWinner().getPlayer().getDisplayName());
            getScore(o, 6, "&2");
            getScore(o, 6, "&eTotal Kills &8» &f%s", getGamePlayer().getKills());
            getScore(o, 5, "&eTotal Points &8» &f%s", getGamePlayer().getPoints());
            getScore(o, 4, "&0");
            getScore(o, 4, "&eBy &8» &fiHDeveloper");
        } else if (Game.getState().equals(GameState.RESTARTING)) {
            getScore(o, 6, "&6");
            getScore(o, 5, "&eRestarting...");
            getScore(o, 4, "&1");
            getScore(o, 3, "&ePlayers: &f%s", Game.getAllPlayers().size());
            getScore(o, 2, "&9");
        }
        getScore(o, 2, "&eVersion &8» &f0.2");
        getScore(o, 1, "&1-------------");
        getScore(o, 0, "&emc.ihdeveloper.me");
    }

    private void getScore(Objective o, int pos, String format, Object... args) {
        Score score = o.getScore(iHDeveloperAPI.color(format, args));
        score.setScore(pos);
    }

    private Team getTeam(Scoreboard sb, String name) {
        Team t = null;
        try {
            t = sb.getTeam(name);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        if (t != null)
            t.unregister();
        t = sb.registerNewTeam(name);
        return t;
    }
}
