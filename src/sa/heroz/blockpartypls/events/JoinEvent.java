package sa.heroz.blockpartypls.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
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
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    p.sendSub(37);
    p.send("             &9&lBlock&3&lParty&e&l+ &b| &7V0.2 &b| &e&nNEW GAME!", new Object[0]);
    p.send("&b&l-|- &6&l5 &e&lNEW MODES!", new Object[0]);
    p.send("&b&l-|- &9/stats &4&lBETA", new Object[0]);
    p.send("&b&l-|- &e&lNEW LOBBY!", new Object[0]);
    p.send("&b&l-|- &c&lFix Bugs & Glitchs", new Object[0]);
    p.send("&b&l-|- &9&lSPECATE MODE &e&lNEW!", new Object[0]);
    p.send("&b&l-|-", new Object[0]);
    p.send("&b&l-|-         &eBy &8» &fiHDeveloper", new Object[0]);
    p.sendSub(37);
    p.clearInv();
    p.getPlayer().setLevel(0);
    p.getPlayer().setExp(0.0F);
    Game.add(p);
    e.setJoinMessage("");
  }
  
  private void load() {}
}
