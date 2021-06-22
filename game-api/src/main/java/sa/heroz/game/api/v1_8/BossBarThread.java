package sa.heroz.game.api.v1_8;

import java.security.SecureRandom;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.thread.GameThread;
import me.iHDeveloper.api.thread.GameThreadManager;
import me.iHDeveloper.api.thread.GameThreadOptions;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBarAPI;
import sa.heroz.game.HerozGame;

@GameThreadOptions(1500)
public class BossBarThread implements GameThread {
  public boolean run(int sec, GameThreadManager gtm) {
    try {
      if (!HerozGame.isStarted()) {
        onStop();
        return false;
      } 
      if (!HerozGame.canUseTitle()) {
        onStop();
        return false;
      } 
    } catch (Exception ex) {
      onStop();
      return false;
    } 
    if (sec == 1400 || sec == 1000 || sec == 500)
      reloadAll(); 
    if (sec <= 0)
      gtm.reset(); 
    return true;
  }
  
  public void onStop() {
    for (Player player : Bukkit.getOnlinePlayers())
      BossBarAPI.removeAllBars(player); 
  }
  
  private void reloadAll() {
    for (Player player : Bukkit.getOnlinePlayers()) {
      HDPlayer p = iHDeveloperAPI.getPlayer(player.getName());
      reload(p);
    } 
  }
  
  private static final SecureRandom ran = new SecureRandom();
  
  private void reload(HDPlayer p) {
    String playing = "&e&lPLAYING";
    String gameName = "&f&l" + HerozGame.getName();
    String on = "&e&lON";
    String[] colors = { "b", "a", "6", "9", "c", "d" };
    String link = "MC.IHDEVELOPER.ME";
    int color = ran.nextInt(colors.length);
    String text = playing + " " + gameName + " " + on + " &" + colors[color] + "&l" + link;
    BossBarAPI.removeAllBars(p.getPlayer());
    displayBossBar(p, iHDeveloperAPI.color(text));
  }
  
  private void displayBossBar(HDPlayer p, String text) {
    BossBarAPI.addBar(p.getPlayer(), (BaseComponent)new TextComponent(text), BossBarAPI.Color.PURPLE, BossBarAPI.Style.NOTCHED_20, 1.0F, new BossBarAPI.Property[0]);
  }
}
