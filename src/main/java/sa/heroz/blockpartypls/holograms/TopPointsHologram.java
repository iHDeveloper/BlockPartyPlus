package sa.heroz.blockpartypls.holograms;

import java.util.ArrayList;
import me.iHDeveloper.api.hologram.Hologram;
import me.iHDeveloper.api.iHDeveloperAPI;
import sa.heroz.blockpartypls.until.Settings;

public class TopPointsHologram extends Hologram {
  private static ArrayList<TopPointsHologram> list = new ArrayList<>();
  
  public static void destoryAllHolograms() {
    for (TopPointsHologram h : list)
      h.hideAll(); 
  }
  
  public TopPointsHologram() {
    super(Settings.holo_topPoints, 0.4D);
    list.add(this);
  }
  
  public void beforeUpdate() {
    getTextList().clear();
    addText(iHDeveloperAPI.color("&6&lTop Points", new Object[0]));
    addText(iHDeveloperAPI.color("&e&l-----------------", new Object[0]));
    addText(iHDeveloperAPI.color("&8&lComing soon", new Object[0]));
  }
}
