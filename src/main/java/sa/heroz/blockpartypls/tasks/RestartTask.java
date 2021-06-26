package sa.heroz.blockpartypls.tasks;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.tasks.GameTask;
import me.iHDeveloper.api.tasks.GameTaskOptions;
import me.iHDeveloper.api.tasks.GameTaskRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sa.heroz.blockpartypls.until.Chat;

import static me.iHDeveloper.api.util.TimeUtils.SECONDS;

@GameTaskOptions(ticks = 15 * SECONDS)
public class RestartTask implements GameTask {
    public void run(int ticks, GameTaskRunnable runnable) {
        if (ticks % SECONDS != 0) {
            return;
        }
        final int seconds = ticks / SECONDS;

        if (seconds == 15 || seconds == 10 || (seconds <= 5 && seconds >= 1)) {
            Chat.broadcast("§eThe server will restart in§c %s§e seconds.", seconds);
            return;
        }

        if (seconds == 0) {
            Chat.broadcast("&eThe server is restarting...");
            Bukkit.getScheduler().runTaskLater(iHDeveloperAPI.getPlugin(), () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.kickPlayer(iHDeveloperAPI.color("&eYou have been kicked because the server is restarting..."));
                }

//              Bukkit.spigot().restart(); unsafe way to restart!!!
                Bukkit.shutdown();
            }, 1L);
            runnable.stop();
        }
    }
}
