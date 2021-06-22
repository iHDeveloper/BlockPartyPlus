package me.iHDeveloper.api.spectate.ui;

import java.util.ArrayList;
import java.util.List;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.spectate.SpectateSystem;
import me.iHDeveloper.api.ui.ComponentItemEvent;
import me.iHDeveloper.api.ui.Folder;
import me.iHDeveloper.api.ui.Form;
import me.iHDeveloper.api.ui.Image;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CompassFolder extends Folder implements ComponentItemEvent {
  public CompassFolder() {
    super(Material.COMPASS, 1);
    setDisplayName("&8&l-|- &f&lCompass &8&l-|-");
    List<String> list = new ArrayList<>();
    list.add(iHDeveloperAPI.color("&7Click to teleport to"));
    list.add(iHDeveloperAPI.color("&7any player in the game."));
    setLore(list);
    setNumber(1);
  }
  
  public void onLoad() {
    setListener(this);
  }
  
  public void onInteract(PlayerInteractEvent e) {
    HDPlayer clicker = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    if (!SpectateSystem.is(clicker))
      return; 
    Form form = new Form(54, "&7Click to spectate");
    List<PlayerButton> buttons = new ArrayList<>();
    for (Player player : Bukkit.getOnlinePlayers()) {
      HDPlayer p = iHDeveloperAPI.getPlayer(player.getName());
      if (!SpectateSystem.is(p)) {
        buttons.add(new PlayerButton(p));
        assert p != null;
        Debug.log("added " + p.getName());
      } 
    } 
    for (int i = 0; i < 9; ) {
      Image image = new Image(Material.STAINED_GLASS_PANE, 1, 15);
      image.setDisplayName("&kIII");
      form.addItem(i, image);
      i++;
    } 
    int x = 8;
    for (PlayerButton button : buttons) {
      x++;
      form.addItem(x, button);
    } 
    if (x == 8) {
      Image image = new Image(Material.BARRIER, 1);
      image.setDisplayName("&cNone");
      form.addItem(22, image);
    } 
    for (int j = 45; j < 54; ) {
      Image image = new Image(Material.STAINED_GLASS_PANE, 1, 15);
      image.setDisplayName("&kIII");
      form.addItem(j, image);
      j++;
    } 
    form.show(clicker);
    e.setCancelled(true);
  }
  
  public void onClick(InventoryClickEvent e) {}
}
