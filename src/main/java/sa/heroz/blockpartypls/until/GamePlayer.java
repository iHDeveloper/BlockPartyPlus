package sa.heroz.blockpartypls.until;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GamePlayerRole;
import sa.heroz.blockpartypls.scoreboard.GameScoreboard;

public class GamePlayer {
  private final Player player;
  
  private GameScoreboard scoreboard;
  
  private int kills;
  
  private int points;
  
  public GamePlayer(Player player) {
    this.player = player;
    init();
  }
  
  private void init() {
    this.scoreboard = new GameScoreboard(this);
    this.kills = 0;
    this.points = 0;
  }
  
  public Player getPlayer() {
    return this.player;
  }
  
  public GameScoreboard getScoreboard() {
    return this.scoreboard;
  }
  
  public int getKills() {
    return this.kills;
  }
  
  public int getPoints() {
    return this.points;
  }
  
  public GamePlayerRole getRole() {
    return Game.getPlayerRole(this);
  }
  
  public void giveKill(String reason) {
    this.kills++;
    Debug.log("Updated Kills in %s to %s", new Object[] { getPlayer().getName(), Integer.valueOf(this.kills) });
    getPlayer().send(iHDeveloperAPI.format(Settings.kill, 
          new String[] { "reason" }, new String[] { reason }), new Object[0]);
    getScoreboard().update();
  }
  
  public void givePoints(int points, String reason) {
    this.points += points;
    Debug.log("Updated Points in %s to %s", new Object[] { getPlayer().getName(), Integer.valueOf(points) });
    getPlayer().send(iHDeveloperAPI.format(Settings.points, 
          new String[] { "points", "reason" }, new String[] { points, reason }), new Object[0]);
    getScoreboard().update();
  }
}
