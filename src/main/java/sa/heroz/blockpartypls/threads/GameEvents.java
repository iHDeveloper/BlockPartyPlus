package sa.heroz.blockpartypls.threads;

import sa.heroz.blockpartypls.until.Chat;
import sa.heroz.blockpartypls.until.TempSettings;

class GameEvents {
  private final GameThread a;
  
  public GameEvents(GameThread a) {
    this.a = a;
  }
  
  void deathmatch() {
    int round = 20 - this.a.round;
    if (round == 0) {
      TempSettings.pvp = false;
      Chat.broadcast("&4&lDeathMatch! &eBecareful from everything.", new Object[0]);
    } else if (round == 15 || round == 10 || (round <= 5 && round > 0)) {
      Chat.broadcast("&7[&cDeathMatch&7] &f&l%s &erounds until to start deathmatch!", new Object[] { Integer.valueOf(round) });
    } 
  }
}
