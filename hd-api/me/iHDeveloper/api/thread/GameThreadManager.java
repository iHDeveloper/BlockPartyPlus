package me.iHDeveloper.api.thread;

public class GameThreadManager implements Runnable {
  private boolean s = false;
  
  private boolean p = false;
  
  private int ms;
  
  private final int DEFAULT_MS;
  
  private final GameThread t;
  
  public GameThreadManager(GameThread thread) {
    this.t = thread;
    GameThreadOptions options = thread.getClass().<GameThreadOptions>getAnnotation(GameThreadOptions.class);
    if (options == null) {
      this.DEFAULT_MS = 0;
      return;
    } 
    this.DEFAULT_MS = options.value();
    this.ms = this.DEFAULT_MS;
  }
  
  public void start() {
    if (this.s)
      return; 
    this.s = true;
    this.p = false;
    (new Thread(this)).start();
  }
  
  public void stop() {
    this.p = true;
    reset();
  }
  
  public void reset() {
    this.ms = this.DEFAULT_MS;
  }
  
  public void run() {
    while (!this.p) {
      try {
        boolean b = this.t.run(this.ms, this);
        if (!b)
          return; 
        try {
          Thread.sleep(100L);
        } catch (Exception ex) {
          ex.printStackTrace();
        } 
        this.ms -= 100;
      } catch (Exception ex) {
        ex.printStackTrace();
      } 
    } 
  }
  
  public int getMS() {
    return this.ms;
  }
}
