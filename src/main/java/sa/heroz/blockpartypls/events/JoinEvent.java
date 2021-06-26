package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sa.heroz.blockpartypls.game.Game;

public class JoinEvent implements Listener {
  public JoinEvent() {
    load();
  }
  
  @EventHandler(priority = EventPriority.HIGH)
  public void onJoin(PlayerJoinEvent e) {
    HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    p.clearInv();
    p.getPlayer().setLevel(0);
    p.getPlayer().setExp(0.0F);
    Game.add(p);
    e.setJoinMessage("");
  }
  
  private void load() {}
}
