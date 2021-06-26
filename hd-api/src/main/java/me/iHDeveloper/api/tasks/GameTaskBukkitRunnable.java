package me.iHDeveloper.api.tasks;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

/**
 * Wrapper for {@link GameTask} with extra functionalities
 */
public final class GameTaskBukkitRunnable extends BukkitRunnable implements GameTaskRunnable {
    private final GameTask task;
    private final int defaultStartTicks;

    private BukkitTask bukkitTask;
    private int ticks;

    public GameTaskBukkitRunnable(GameTask task, int defaultStartTicks) {
        this.task = task;
        this.defaultStartTicks = defaultStartTicks;
        this.reset();
    }

    public void start() {
        if (this.bukkitTask != null) {
            Debug.warn(task.getClass().getCanonicalName() + " tried to start with already started task!");
            Debug.warn("Cancelling the current task and starting a new one...");
            Debug.warn("Please make sure you don't start an already task again. It might be a bug!");
            return;
        }

        this.bukkitTask = super.runTaskTimer(iHDeveloperAPI.getPlugin(),0L, 1L);
    }

    public void stop() {
        if (this.bukkitTask == null) {
            return;
        }

        this.bukkitTask.cancel();
        this.bukkitTask = null;
    }

    public void reset() {
        this.ticks = this.defaultStartTicks;
    }

    @Override
    public void run() {
        if (this.ticks <= -1) {
            this.stop();
            return;
        }

        this.task.run(this.ticks, this);
        this.ticks--;
    }

}
