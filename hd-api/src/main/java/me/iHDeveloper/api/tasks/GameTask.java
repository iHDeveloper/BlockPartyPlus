package me.iHDeveloper.api.tasks;

public interface GameTask {
    void run(int ticks, GameTaskRunnable runnable);
}
