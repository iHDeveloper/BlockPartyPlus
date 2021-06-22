package sa.heroz.blockpartypls.until.round;

import me.iHDeveloper.api.iHDeveloperAPI;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.Floor;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;

public abstract class Round {
  private final int id;
  
  private final String name;
  
  private final RoundType type;
  
  private String displayname;
  
  public Round(int id, String name, RoundType type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public RoundType getType() {
    return this.type;
  }
  
  public void setDisplayname(String displayname) {
    this.displayname = iHDeveloperAPI.color(displayname, new Object[0]);
  }
  
  public String getDisplayname() {
    return this.displayname;
  }
  
  public abstract void start();
  
  public abstract void random(int paramInt);
  
  public abstract void color(Floor paramFloor);
  
  public abstract void clear(Floor paramFloor);
  
  public abstract void reset(Floor paramFloor);
  
  public void goToLoading() {
    for (GamePlayer p : Game.getAlivePlayers())
      p.getPlayer().getPlayer().teleport(Settings.loading); 
    sendTitle(String.valueOf(getDisplayname()) + " &e&lMode", "&8&l» Loading....", 40, 20, 40);
  }
  
  public void goToGame() {
    for (GamePlayer p : Game.getAlivePlayers())
      p.getPlayer().getPlayer().teleport(Settings.game); 
    sendTitle(getDisplayname(), "&2&l»  Starting...", 10, 20, 10);
  }
  
  public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
    for (GamePlayer p : Game.getAlivePlayers())
      p.getPlayer().sendTitle(title, subtitle, fadeIn, stay, fadeOut); 
  }
}
