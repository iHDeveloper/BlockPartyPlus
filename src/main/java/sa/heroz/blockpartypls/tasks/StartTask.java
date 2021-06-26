package sa.heroz.blockpartypls.tasks;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.tasks.GameTask;
import me.iHDeveloper.api.tasks.GameTaskOptions;
import me.iHDeveloper.api.tasks.GameTaskRunnable;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.Chat;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;

import static me.iHDeveloper.api.util.TimeUtils.SECONDS;

@GameTaskOptions(ticks = 30 * SECONDS)
public class StartTask implements GameTask {

    public void run(int ticks, GameTaskRunnable runnable) {
        if (iHDeveloperAPI.getPlayers().size() <= Settings.maxPlayers / 2) {
            runnable.reset();
            return;
        }

        if (ticks % SECONDS != 0) {
            return;
        }

        final int seconds = ticks / SECONDS;

        if (seconds == 0) {
            Game.play();
            runnable.stop();
            return;
        }

        if (seconds == 30 || seconds == 15 || seconds == 10 || (seconds <= 5 && seconds >= 1)) {
            Chat.broadcast("§eThe game will start in§c %s§e seconds", seconds);
        }

        for (GamePlayer player : Game.getAlivePlayers()) {
            player.getPlayer().getPlayer().setLevel(seconds);
            Game.updateAll();
        }
    }

}
