package sa.heroz.blockpartypls.scoreboard;

import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;
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

public class GameScoreboard {
  private final GamePlayer player;
  
  private Scoreboard sb;
  
  public GameScoreboard(final GamePlayer player) {
    this.player = player;
    Bukkit.getScheduler().scheduleSyncDelayedTask(
        (Plugin)iHDeveloperAPI.getPlugin(), 
        new Runnable() {
          public void run() {
            ScoreboardManager sbm = Bukkit.getScoreboardManager();
            GameScoreboard.this.sb = sbm.getNewScoreboard();
            GameScoreboard.this.update();
            player.getPlayer().getPlayer().setScoreboard(GameScoreboard.this.sb);
          }
        });
  }
  
  public Scoreboard getScoreboard() {
    return this.sb;
  }
  
  public GamePlayer getGamePlayer() {
    return this.player;
  }
  
  public void update() {
    Team npcs = getTeam(getScoreboard(), "znpcs");
    npcs.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
    npcs.setPrefix(iHDeveloperAPI.color("&8[NPC] ", new Object[0]));
    npcs.addEntry("Author");
    Team spectators = getTeam(this.sb, "spectators");
    spectators.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
    spectators.setCanSeeFriendlyInvisibles(true);
    spectators.setPrefix(iHDeveloperAPI.color("&c&lDEAD &8|&f ", new Object[0]));
    Team players = getTeam(getScoreboard(), "!players");
    players.setPrefix(iHDeveloperAPI.color("&9&lHERO &8|&f ", new Object[0]));
    Team winner = getTeam(getScoreboard(), "!!!winner");
    winner.setPrefix(iHDeveloperAPI.color("&aWINNER &8|&f ", new Object[0]));
    Team losers = getTeam(getScoreboard(), "losers");
    losers.setPrefix(iHDeveloperAPI.color("&cLoser &8|&f ", new Object[0]));
    for (GamePlayer gamePlayer : Game.getAllPlayers()) {
      spectators.removePlayer((OfflinePlayer)gamePlayer.getPlayer().getPlayer());
      players.removePlayer((OfflinePlayer)gamePlayer.getPlayer().getPlayer());
    } 
    if (!Game.getState().equals(GameState.FINISH)) {
      for (GamePlayer gamePlayer : Game.getSpectatorsPlayers())
        spectators.addPlayer((OfflinePlayer)gamePlayer.getPlayer().getPlayer()); 
      for (GamePlayer p : Game.getAlivePlayers())
        players.addPlayer((OfflinePlayer)p.getPlayer().getPlayer()); 
    } else {
      winner.addPlayer((OfflinePlayer)Game.getWinner().getPlayer().getPlayer());
      for (GamePlayer loser : Game.getAllPlayers()) {
        if (!loser.getPlayer().getUUID().equals(Game.getWinner().getPlayer().getUUID()))
          losers.addPlayer((OfflinePlayer)loser.getPlayer().getPlayer()); 
      } 
    } 
    Objective o = this.sb.getObjective("sidebar");
    if (o != null)
      o.unregister(); 
    o = this.sb.registerNewObjective("sidebar", "");
    o.setDisplaySlot(DisplaySlot.SIDEBAR);
    o.setDisplayName(iHDeveloperAPI.color("&6&lBLOCKPARTY+", new Object[0]));
    String version = "&eVersion &8» &f0.2";
    if (Game.getState().equals(GameState.WAITING)) {
      getScore(o, 6, "&6", new Object[0]);
      getScore(o, 5, "&eWaiting...", new Object[0]);
      getScore(o, 4, "&1", new Object[0]);
      getScore(o, 3, "&ePlayers: &a%s&e/&c%s", new Object[] { Integer.valueOf(Game.getAlivePlayers().size()), Integer.valueOf(Settings.maxPlayers) });
      getScore(o, 2, "&9", new Object[0]);
      getScore(o, 2, "&eVersion &8» &f0.2", new Object[0]);
    } else if (Game.getState().equals(GameState.STARTING)) {
      getScore(o, 6, "&6", new Object[0]);
      try {
        getScore(o, 5, "&eStarting in %s", new Object[] { Integer.valueOf(Game.startGTM.getMS() / 1000) });
      } catch (NullPointerException ex) {
        getScore(o, 5, "&eStarting...", new Object[0]);
      } 
      getScore(o, 4, "&1", new Object[0]);
      getScore(o, 3, "&ePlayers: &a%s&e/&c%s", new Object[] { Integer.valueOf(Game.getAlivePlayers().size()), Integer.valueOf(Settings.maxPlayers) });
      getScore(o, 2, "&9", new Object[0]);
    } else if (Game.getState().equals(GameState.IN_GAME)) {
      getScore(o, 9, "&6", new Object[0]);
      getScore(o, 8, "&ePlayers: &f%s", new Object[] { Integer.valueOf(Game.getAlivePlayers().size()) });
      getScore(o, 7, "&eSpectators: &f%s", new Object[] { Integer.valueOf(Game.getSpectatorsPlayers().size()) });
      getScore(o, 6, "&1", new Object[0]);
      try {
        getScore(o, 6, "&eRound: &f%s", new Object[] { Integer.valueOf(Game.getGameThread().getRound().getId()) });
        getScore(o, 5, "&eMode: &f%s", new Object[] { Game.getGameThread().getRound().getName() });
      } catch (NullPointerException ex) {
        getScore(o, 6, "&eRound: &f&kRRR", new Object[0]);
        getScore(o, 5, "&eMode: &f&kRRR", new Object[0]);
      } 
      getScore(o, 5, "&e&b", new Object[0]);
      getScore(o, 4, "&eKills &8» &f%s", new Object[] { Integer.valueOf(getGamePlayer().getKills()) });
      getScore(o, 3, "&ePoints &8» &f%s", new Object[] { Integer.valueOf(getGamePlayer().getPoints()) });
    } else if (Game.getState().equals(GameState.FINISH)) {
      getScore(o, 9, "&eGame: &fThe End", new Object[0]);
      getScore(o, 8, "&1", new Object[0]);
      getScore(o, 7, "&eWinner &8»", new Object[0]);
      getScore(o, 7, "&f%s", new Object[] { Game.getWinner().getPlayer().getDisplayName() });
      getScore(o, 6, "&2", new Object[0]);
      getScore(o, 6, "&eTotal Kills &8» &f%s", new Object[] { Integer.valueOf(getGamePlayer().getKills()) });
      getScore(o, 5, "&eTotal Points &8» &f%s", new Object[] { Integer.valueOf(getGamePlayer().getPoints()) });
      getScore(o, 4, "&0", new Object[0]);
      getScore(o, 4, "&eBy &8» &fiHDeveloper", new Object[0]);
    } else if (Game.getState().equals(GameState.RESTARTING)) {
      getScore(o, 6, "&6", new Object[0]);
      getScore(o, 5, "&eRestarting...", new Object[0]);
      getScore(o, 4, "&1", new Object[0]);
      getScore(o, 3, "&ePlayers: &f%s", new Object[] { Integer.valueOf(Game.getAllPlayers().size()) });
      getScore(o, 2, "&9", new Object[0]);
    } 
    getScore(o, 2, "&eVersion &8» &f0.2", new Object[0]);
    getScore(o, 1, "&1-------------", new Object[0]);
    getScore(o, 0, "&b&lHeroz&a&lSA &e&lNET", new Object[0]);
  }
  
  private Score getScore(Objective o, int pos, String format, Object... args) {
    Score score = o.getScore(iHDeveloperAPI.color(format, args));
    score.setScore(pos);
    return score;
  }
  
  private Team getTeam(Scoreboard sb, String name) {
    Team t = null;
    try {
      t = sb.getTeam(name);
    } catch (NullPointerException ex) {
      t = null;
    } 
    if (t != null)
      t.unregister(); 
    t = sb.registerNewTeam(name);
    return t;
  }
}
