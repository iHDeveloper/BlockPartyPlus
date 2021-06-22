package sa.heroz.blockpartypls.holograms;

import java.util.ArrayList;
import me.iHDeveloper.api.hologram.Hologram;
import me.iHDeveloper.api.iHDeveloperAPI;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;

public class StatsHologram extends Hologram {
  private static final ArrayList<StatsHologram> list = new ArrayList<>();
  
  private final GamePlayer player;
  
  public static void destoryAllHolograms() {
    for (StatsHologram h : list)
      h.hideAll(); 
  }
  
  public StatsHologram(GamePlayer player) {
    super(Settings.holo_stats, 0.4D);
    this.player = player;
    update();
    list.add(this);
  }
  
  public void beforeUpdate() {
    getTextList().clear();
    addText(iHDeveloperAPI.color("&e&lSTATS &8&l|| &f&l%s", getPlayer().getPlayer().getName()));
    addText(iHDeveloperAPI.color("&e&l----------------------"));
    addText(iHDeveloperAPI.color("&e&lKills &7&l» &f&l%s", getPlayer().getKills()));
    addText(iHDeveloperAPI.color("&e&lPoints &7&l» &f&l%s", getPlayer().getPoints()));
    switch (Game.getPlayerRole(getPlayer())) {
      case PLAYER:
        addText(iHDeveloperAPI.color("&e&lRole &7&l» %s", "&a&lPLAYER"));
        break;
      case WINNER:
        addText(iHDeveloperAPI.color("&e&lRole &7&l» %s", "&b&lWINNER"));
        break;
      case LOSER:
        addText(iHDeveloperAPI.color("&e&lRole &7&l» %s", "&4&lLOSER"));
        break;
      default:
        addText(iHDeveloperAPI.color("&e&lRole &7&l» %s", "&c&lDEAD"));
        break;
    } 
  }
  
  public void afterUpdate() {
    hideAll();
    showPlayer(this.player.getPlayer());
  }
  
  public GamePlayer getPlayer() {
    return this.player;
  }
}
