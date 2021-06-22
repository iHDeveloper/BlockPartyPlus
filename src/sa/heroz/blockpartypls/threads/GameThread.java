package sa.heroz.blockpartypls.threads;

import java.security.SecureRandom;
import java.util.ArrayList;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.thread.GameThread;
import me.iHDeveloper.api.thread.GameThreadManager;
import me.iHDeveloper.api.thread.GameThreadOptions;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.game.GameState;
import sa.heroz.blockpartypls.rounds.ClayRound;
import sa.heroz.blockpartypls.rounds.DeathMatchRound;
import sa.heroz.blockpartypls.rounds.ForestRound;
import sa.heroz.blockpartypls.rounds.GlassRound;
import sa.heroz.blockpartypls.rounds.KillRound;
import sa.heroz.blockpartypls.rounds.MineRound;
import sa.heroz.blockpartypls.rounds.NetherRound;
import sa.heroz.blockpartypls.rounds.NormalRound;
import sa.heroz.blockpartypls.until.Floor;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;
import sa.heroz.blockpartypls.until.round.Round;

@GameThreadOptions(13500)
public class GameThread implements GameThread {
  boolean a;
  
  public int round;
  
  private GameEvents g;
  
  private final Floor floor;
  
  private Round theRoundClass;
  
  private final SecureRandom ran;
  
  private boolean b;
  
  private final Boolean c;
  
  public GameThread() {
    this.theRoundClass = null;
    this.ran = new SecureRandom();
    this.b = false;
    this.c = new Boolean(false);
    this.floor = new Floor(this);
    this.a = false;
    this.round = 1;
    this.g = new GameEvents(this);
    this.theRoundClass = createRound();
  }
  
  public Floor getFloor() {
    return this.floor;
  }
  
  public Round getRound() {
    return this.theRoundClass;
  }
  
  private Round createRound() {
    if (this.round >= 20)
      return new DeathMatchRound(this.round); 
    ArrayList<Round> rounds = new ArrayList<>();
    rounds.add(new NormalRound(this.round));
    rounds.add(new KillRound(this.round));
    rounds.add(new ClayRound(this.round));
    rounds.add(new GlassRound(this.round));
    rounds.add(new MineRound(this.round));
    rounds.add(new NetherRound(this.round));
    rounds.add(new ForestRound(this.round));
    int roundID = this.ran.nextInt(rounds.size());
    Round theNextRound = rounds.get(roundID);
    return theNextRound;
  }
  
  public boolean run(int sec, GameThreadManager gtm) {
    if (Game.getState() == GameState.RESTARTING)
      return false; 
    if (Game.getState() == GameState.FINISH) {
      if (sec == 5000) {
        GameThreadManager thread = new GameThreadManager(new RestartThread());
        thread.start();
        return false;
      } 
      if (!this.b) {
        this.b = true;
        this.theRoundClass.reset(getFloor());
        Game.updateAll();
        gtm.reset();
      } 
      return true;
    } 
    if (!this.c.a()) {
      this.c.A(true);
      Bukkit.getScheduler().runTask((Plugin)iHDeveloperAPI.getPlugin(), new Runnable() {
            public void run() {
              for (GamePlayer gp : Game.getAllPlayers()) {
                try {
                  int pos1 = Settings.pos1.getBlockY() - 1;
                  int pos2 = gp.getPlayer().getLocation().getBlockY();
                  if (pos2 < pos1)
                    Game.eliminate(gp.getPlayer(), gp.getPlayer().getLocation()); 
                } catch (Exception exception) {}
              } 
              GameThread.this.c.A(false);
            }
          });
    } 
    if (sec == 13000) {
      debug("Start Step");
      this.theRoundClass.start();
      this.a = true;
    } 
    if (sec == 12000) {
      debug("Coloring floor...");
      this.theRoundClass.color(getFloor());
      this.a = true;
    } 
    if (sec == 11000 || sec == 10000 || sec == 9000) {
      debug("Randoming color...");
      if (sec == 11000) {
        this.theRoundClass.random(1);
      } else if (sec == 10000) {
        this.theRoundClass.random(2);
      } else if (sec == 9000) {
        this.theRoundClass.random(3);
      } 
      this.a = true;
    } 
    if (sec == 8000 || sec == 7000 || sec == 6000) {
      debug("Colding down...");
      return true;
    } 
    if (sec == 5500) {
      debug("Clearing the floor...");
      this.theRoundClass.clear(getFloor());
      this.a = true;
    } 
    if (sec == 5000 || sec == 4000 || sec == 3000) {
      debug("Colding down...");
      return true;
    } 
    if (sec == 1200) {
      debug("Reseting the round...");
      this.theRoundClass.reset(getFloor());
      gtm.reset();
      this.round++;
      this.g.deathmatch();
      this.theRoundClass = createRound();
      for (GamePlayer gamePlayer : Game.getAllPlayers()) {
        gamePlayer.getPlayer().getPlayer().setExp(0.0F);
        gamePlayer.getPlayer().getPlayer().setLevel(this.theRoundClass.getId());
        gamePlayer.givePoints(50, "Still Alive");
      } 
      Game.updateAll();
      this.a = true;
    } 
    return true;
  }
  
  private void debug(String format) {
    Debug.log("[GAME-THREAD (ROUND-%s)] %s", new Object[] { Integer.valueOf(this.round), format });
  }
}
