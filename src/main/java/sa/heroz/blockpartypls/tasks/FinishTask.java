package sa.heroz.blockpartypls.tasks;

import me.iHDeveloper.api.tasks.GameTask;
import me.iHDeveloper.api.tasks.GameTaskOptions;
import me.iHDeveloper.api.tasks.GameTaskRegistry;
import me.iHDeveloper.api.tasks.GameTaskRunnable;
import me.iHDeveloper.api.util.TimeUtils;
import sa.heroz.blockpartypls.game.Game;

@GameTaskOptions(ticks = 5 * TimeUtils.SECONDS)
public final class FinishTask implements GameTask {
    private static final int SECONDS = 20;

    @Override
    public void run(int ticks, GameTaskRunnable runnable) {
        if (ticks == 5 * SECONDS) {
            GameTaskRegistry.start(RestartTask.class);
            return;
        }

        if (ticks == 0) {
            Game.getCurrentRound().reset(Game.getFloor());
            Game.updateAll();
            runnable.stop();
        }
    }
}
