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
  public boolean run(int ms, GameThreadManager gtm) {
    int seconds = ms / 1000;

    if (seconds == 15 || seconds == 10 || (seconds <= 5 && seconds >= 1)) {
      Chat.broadcast("§eThe server will restart in§c %s§e seconds.", seconds);
    }

    if (seconds == 0) {
      Chat.broadcast("&eThe server is restarting...");
      Bukkit.getScheduler().runTaskLater(iHDeveloperAPI.getPlugin(), () -> {
        for (Player player : Bukkit.getOnlinePlayers())
          player.kickPlayer(iHDeveloperAPI.color("&eYou have been kicked because the server is restarting..."));
//              Bukkit.spigot().restart(); unsafe way to restart!!!
        Bukkit.shutdown();
      }, 1L);
      return false;
    }
    return true;
  }
}
