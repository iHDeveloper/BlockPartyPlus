package sa.heroz.blockpartypls.threads;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.thread.GameThread;
import me.iHDeveloper.api.thread.GameThreadManager;
import me.iHDeveloper.api.thread.GameThreadOptions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import sa.heroz.blockpartypls.until.Chat;

@GameThreadOptions(15000)
public class RestartThread implements GameThread {
  public boolean run(int sec, GameThreadManager gtm) {
    if (sec == 15000) {
      Chat.broadcast("&eThe server will be restart in &c%s &esec", new Object[] { Integer.valueOf(15) });
    } else if (sec == 10000) {
      Chat.broadcast("&eThe server will be restart in &c%s &esec", new Object[] { Integer.valueOf(10) });
    } else if (sec == 5000) {
      Chat.broadcast("&eThe server will be restart in &c%s &esec", new Object[] { Integer.valueOf(5) });
    } else if (sec == 4000) {
      Chat.broadcast("&eThe server will be restart in &c%s &esec", new Object[] { Integer.valueOf(4) });
    } else if (sec == 3000) {
      Chat.broadcast("&eThe server will be restart in &c%s &esec", new Object[] { Integer.valueOf(3) });
    } else if (sec == 2000) {
      Chat.broadcast("&eThe server will be restart in &c%s &esec", new Object[] { Integer.valueOf(2) });
    } else if (sec == 1000) {
      Chat.broadcast("&eThe server will be restart in &c%s &esec", new Object[] { Integer.valueOf(1) });
    } else if (sec == 0) {
      Chat.broadcast("&eThe server is restarting...", new Object[0]);
      Bukkit.getScheduler().runTask((Plugin)iHDeveloperAPI.getPlugin(), new Runnable() {
            public void run() {
              for (Player player : Bukkit.getOnlinePlayers())
                player.kickPlayer(iHDeveloperAPI.color("&eYou have been kicked becuase the server is restarting...", new Object[0])); 
              Bukkit.spigot().restart();
            }
          });
      return false;
    } 
    return true;
  }
}
