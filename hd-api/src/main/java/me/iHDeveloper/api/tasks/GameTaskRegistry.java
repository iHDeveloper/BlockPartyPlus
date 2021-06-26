package me.iHDeveloper.api.tasks;

import me.iHDeveloper.api.Debug;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class GameTaskRegistry {
    private static final Map<Class<?>, GameTaskBukkitRunnable> tasks = new HashMap<>();

    public static void register(Class<? extends GameTask> taskClass) {
        GameTaskOptions options = taskClass.<GameTaskOptions>getAnnotation(GameTaskOptions.class);
        if (options == null) {
            Debug.error("Failed to register a task: " + taskClass.getCanonicalName() + " (make sure it has @GameTaskOptions?)");
            return;
        }

        GameTask taskInstance;
        try {
            Constructor<? extends GameTask> emptyConstructor = taskClass.getConstructor();
            taskInstance = emptyConstructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            Debug.error("Can't create a new instance from " + taskClass.getCanonicalName() + "due to " + e.getMessage() + " (make sure it has an empty constructor)");
            return;
        }

        tasks.put(taskClass, new GameTaskBukkitRunnable(taskInstance, options.ticks()));
    }

    public static void start(Class<? extends GameTask> taskClass) {
        GameTaskRunnable runnable = tasks.get(taskClass);
        if (runnable == null) {
            Debug.warn("Game task runnable for " + taskClass.getCanonicalName() + " doesn't exist! (did you register it?)");
            return;
        }

        runnable.start();
    }

    public static void stop(Class<? extends GameTask> taskClass) {
        GameTaskRunnable runnable = tasks.get(taskClass);
        if (runnable == null) {
            Debug.warn("Game task runnable for " + taskClass.getCanonicalName() + " doesn't exist! (did you register it?)");
            return;
        }

        runnable.stop();
    }

    public static void reset(Class<? extends GameTask> taskClass) {
        GameTaskRunnable runnable = tasks.get(taskClass);
        if (runnable == null) {
            Debug.warn("Game task runnable for " + taskClass.getCanonicalName() + " doesn't exist! (did you register it?)");
            return;
        }

        runnable.reset();
    }
}
