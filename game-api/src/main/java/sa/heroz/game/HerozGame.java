package sa.heroz.game;

import me.iHDeveloper.api.tasks.GameTaskRegistry;
import sa.heroz.game.api.GameAPI;
import sa.heroz.game.api.v1_8.BossBarTask;
import sa.heroz.game.api.v1_8.HerozTitle;
import sa.heroz.game.until.Console;

import java.lang.reflect.Method;

public class HerozGame {
    private static HerozGame instance = null;
    private final GameAPI api;
    private boolean s;

    public HerozGame() {
        if (instance != null)
            throw new IllegalAccessError("The HerozGame have more than one instance");
        this.api = Main.getGameAPI();
        this.s = false;
        instance = this;
        GameTaskRegistry.register(BossBarTask.class);
    }

    public GameAPI getAPI() {
        return this.api;
    }

    private static Object i(String a) {
        try {
            Class<?> c = getGameAPI().getClass();
            Method method = null;
            byte b;
            int i;
            Method[] arrayOfMethod;
            for (i = (arrayOfMethod = c.getMethods()).length, b = 0; b < i; ) {
                Method m = arrayOfMethod[b];
                if (m.getName().equals(a))
                    method = m;
                b++;
            }
            assert method != null;
            return method.invoke(getGameAPI());
        } catch (Exception ex) {
            ex.printStackTrace();
            Console.log("&cERR: &7%s", ex.getMessage());
            return null;
        }
    }

    private static void s(String a, Object... b) {
        try {
            Class<?> c = getGameAPI().getClass();
            Method method = null;
            byte b1;
            int i;
            Method[] arrayOfMethod;
            for (i = (arrayOfMethod = c.getMethods()).length, b1 = 0; b1 < i; ) {
                Method m = arrayOfMethod[b1];
                if (m.getName().equals(a))
                    method = m;
                b1++;
            }
            assert method != null;
            method.invoke(getGameAPI(), b);
        } catch (Exception ex) {
            ex.printStackTrace();
            Console.log("&cERR: &7%s", ex.getMessage());
        }
    }

    public static void start() {
        if (isStarted())
            return;
        Console.log("&a###########################################");
        Console.log("&a#>           &eHeroz GameAPI");
        Console.log("&a#>");
        Console.log("&a#> &eGame Name: &7%s", getName());
        Console.log("&a#> &eGame Version: &7%s", getVersion());
        Console.log("&a#> &eGame Author: &7%s", getAuthor());
        Console.log("&a#> &eIs Prototype: &7%s", isPrototype() ? "&atrue" : "&cfalse");
        Console.log("&a#>");
        Console.log("&a#> &eCan use BossBar: &7%s", canUseBossBar() ? "&atrue" : "&cfalse");
        Console.log("&a#> &eCan use Title: &7%s", canUseTitle() ? "&atrue" : "&cfalse");
        Console.log("&a#>");
        Console.log("&a###########################################");
        (getHerozGame()).s = true;
//        (getHerozGame()).bbManager.start();
        GameTaskRegistry.start(BossBarTask.class);
    }

    public static void stop() {
        if (!isStarted())
            return;
        GameTaskRegistry.stop(BossBarTask.class);
        GameTaskRegistry.reset(BossBarTask.class);
        HerozTitle.clearAll();
        (getHerozGame()).s = false;
    }

    public static HerozGame getHerozGame() {
        return instance;
    }

    public static GameAPI getGameAPI() {
        return getHerozGame().getAPI();
    }

    public static String getName() {
        return (String)i("getName");
    }

    public static String getVersion() {
        return (String)i("getVersion");
    }

    public static String getAuthor() {
        return (String)i("getAuthor");
    }

    public static boolean isPrototype() {
        return (Boolean) i("isPrototype");
    }

    public static boolean canUseBossBar() {
        return (Boolean) i("canUseBossBar");
    }

    public static boolean canUseTitle() {
        return (Boolean) i("canUseTitle");
    }

    public static boolean isStarted() {
        return (getHerozGame()).s;
    }

    public static void setName(String name) {
        s("setName", name);
    }

    public static void setVersion(String version) {
        s("setVersion", version);
    }

    public static void setAuthor(String author) {
        s("setAuthor", author);
    }

    public static void setIsPrototype(boolean prototype) {
        s("setIsPrototype", prototype);
    }

    public static void setBossBar(boolean bossbar) {
        s("setBossBar", bossbar);
    }

    public static void setTitle(boolean title) {
        s("setTitle", title);
    }
}
