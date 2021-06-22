package sa.heroz.game;

import java.lang.reflect.Method;
import me.iHDeveloper.api.thread.GameThreadManager;
import sa.heroz.game.api.GameAPI;
import sa.heroz.game.api.v1_8.BossBarThread;
import sa.heroz.game.api.v1_8.HerozTitle;
import sa.heroz.game.until.Console;

public class HerozGame {
  private static HerozGame instance = null;
  
  private final GameAPI api;
  
  private boolean s;
  
  private GameThreadManager bbManager;
  
  private BossBarThread bbThread;
  
  public HerozGame() {
    if (instance != null)
      throw new IllegalAccessError("The HerozGame have more than one instance"); 
    this.api = Main.getGameAPI();
    this.s = false;
    this.bbThread = new BossBarThread();
    this.bbManager = new GameThreadManager(this.bbThread);
    instance = this;
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
      return method.invoke(getGameAPI(), new Object[0]);
    } catch (Exception ex) {
      ex.printStackTrace();
      Console.log("&cERR: &7%s", new Object[] { ex.getMessage() });
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
      method.invoke(getGameAPI(), b);
    } catch (Exception ex) {
      ex.printStackTrace();
      Console.log("&cERR: &7%s", new Object[] { ex.getMessage() });
    } 
  }
  
  public static void start() {
    if (isStarted())
      return; 
    Console.log("&a###########################################", new Object[0]);
    Console.log("&a#>           &eHeroz GameAPI", new Object[0]);
    Console.log("&a#>", new Object[0]);
    Console.log("&a#> &eGame Name: &7%s", new Object[] { getName() });
    Console.log("&a#> &eGame Version: &7%s", new Object[] { getVersion() });
    Console.log("&a#> &eGame Author: &7%s", new Object[] { getAuthor() });
    Console.log("&a#> &eIs Prototype: &7%s", new Object[] { isPrototype() ? "&atrue" : "&cfalse" });
    Console.log("&a#>", new Object[0]);
    Console.log("&a#> &eCan use BossBar: &7%s", new Object[] { canUseBossBar() ? "&atrue" : "&cfalse" });
    Console.log("&a#> &eCan use Title: &7%s", new Object[] { canUseTitle() ? "&atrue" : "&cfalse" });
    Console.log("&a#>", new Object[0]);
    Console.log("&a###########################################", new Object[0]);
    (getHerozGame()).s = true;
    (getHerozGame()).bbManager.start();
  }
  
  public static void stop() {
    if (!isStarted())
      return; 
    (getHerozGame()).bbManager.stop();
    (getHerozGame()).bbManager.reset();
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
    return ((Boolean)i("isPrototype")).booleanValue();
  }
  
  public static boolean canUseBossBar() {
    return ((Boolean)i("canUseBossBar")).booleanValue();
  }
  
  public static boolean canUseTitle() {
    return ((Boolean)i("canUseTitle")).booleanValue();
  }
  
  public static boolean isStarted() {
    return (getHerozGame()).s;
  }
  
  public static void setName(String name) {
    s("setName", new Object[] { name });
  }
  
  public static void setVersion(String version) {
    s("setVersion", new Object[] { version });
  }
  
  public static void setAuthor(String author) {
    s("setAuthor", new Object[] { author });
  }
  
  public static void setIsPrototype(boolean prototype) {
    s("setIsPrototype", new Object[] { Boolean.valueOf(prototype) });
  }
  
  public static void setBossBar(boolean bossbar) {
    s("setBossBar", new Object[] { Boolean.valueOf(bossbar) });
  }
  
  public static void setTitle(boolean title) {
    s("setTitle", new Object[] { Boolean.valueOf(title) });
  }
}
