package sa.heroz.blockpartypls.threads;

import me.iHDeveloper.api.thread.GameThread;
import me.iHDeveloper.api.thread.GameThreadManager;
import me.iHDeveloper.api.thread.GameThreadOptions;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.GamePlayer;

@GameThreadOptions(31000)
public class StartThread implements GameThread {
  private int s = 0;
  
  public boolean run(int ms, GameThreadManager gtm) {
    if (this.s == 0)
      this.s = ms; 
    if (ms >= 0) {
      if (this.s > ms) {
        for (GamePlayer player : Game.getAlivePlayers()) {
          player.getPlayer().getPlayer().setLevel(ms / 1000);
          if (ms == 30000 || ms == 15000 || 
            ms == 10000 || ms == 5000 || 
            ms == 4000 || ms == 3000 || 
            ms != 2000);
          Game.updateAll();
        } 
        this.s = ms - 1000;
      } 
      return true;
    } 
    Game.play();
    return false;
  }
}
