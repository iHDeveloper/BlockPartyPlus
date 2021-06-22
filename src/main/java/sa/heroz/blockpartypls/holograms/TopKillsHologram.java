package sa.heroz.blockpartypls.holograms;

import java.util.ArrayList;
import me.iHDeveloper.api.hologram.Hologram;
import me.iHDeveloper.api.iHDeveloperAPI;
import sa.heroz.blockpartypls.until.Settings;

public class TopKillsHologram extends Hologram {
  private static ArrayList<TopKillsHologram> list = new ArrayList<>();
  
  public static void destoryAllHolograms() {
    for (TopKillsHologram h : list)
      h.hideAll(); 
  }
  
  public TopKillsHologram() {
    super(Settings.holo_topKills, 0.4D);
    list.add(this);
  }
  
  public void beforeUpdate() {
    getTextList().clear();
    addText(iHDeveloperAPI.color("&6&lTop Kills", new Object[0]));
    addText(iHDeveloperAPI.color("&e&l-----------------", new Object[0]));
    addText(iHDeveloperAPI.color("&8&lComing soon", new Object[0]));
  }
  
  public static void main(String[] args) {}
}
