package me.iHDeveloper.api.issue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;

public class Issue {
  private final Exception exception;
  
  private final Player reporter;
  
  private final String id;
  
  public Issue(Player reporter, Exception exception) {
    this.exception = exception;
    this.reporter = reporter;
    this.id = shuffle();
  }
  
  public void save() {
    try {
      File dir = new File(iHDeveloperAPI.getDataFolder() + "/issues");
      dir.mkdir();
      File file = new File(iHDeveloperAPI.getDataFolder() + "/issues/" + getId() + ".txt");
      file.createNewFile();
      PrintWriter writer = new PrintWriter(file);
      getException().printStackTrace(writer);
      writer.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public Player getPlayer() {
    return this.reporter;
  }
  
  public Exception getException() {
    return this.exception;
  }
  
  public String getId() {
    return this.id;
  }
  
  private static String shuffle() {
    String[] a = { 
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
        "k", "l", "mnpqrstuvwxyz" };
    String id = "";
    Random ran = new Random();
    for (int i = 0; i < 7; i++) {
      int x = ran.nextInt(2);
      ran = new Random();
      if (x == 0) {
        id = String.valueOf(id) + ran.nextInt(10);
      } else {
        id = String.valueOf(id) + a[ran.nextInt(a.length)];
      } 
    } 
    return id;
  }
  
  public static void log(Player p, String id) {
    try {
      File file = new File(iHDeveloperAPI.getDataFolder() + "/issues/" + id + ".txt");
      BufferedReader in = new BufferedReader(new FileReader(file));
      p.sendSub();
      p.getPlayer().sendMessage(iHDeveloperAPI.color("&9      &e&lIssue#&7%s", new Object[] { id }));
      while (in.read() != -1)
        p.sendNoPrefix(in.readLine(), new Object[0]); 
      p.sendSub();
      in.close();
    } catch (FileNotFoundException ex) {
      p.sendError("Issue not found!", new Object[0]);
    } catch (IOException ex) {
      p.sendError("Not found main folder", new Object[0]);
    } 
  }
}
