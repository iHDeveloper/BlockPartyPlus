package me.iHDeveloper.api.spectate.ui;

import java.util.ArrayList;
import java.util.List;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.ui.ComponentItemEvent;
import me.iHDeveloper.api.ui.Folder;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayAgainFolder extends Folder implements ComponentItemEvent {
  public PlayAgainFolder() {
    super(Material.PAPER, 1);
    setDisplayName("&a&l-|- &f&lPlay Again &a&l-|-");
    List<String> list = new ArrayList<>();
    list.add(iHDeveloperAPI.color("&7Click to play another game."));
    setLore(list);
    setNumber(1);
  }
  
  public void onLoad() {
    setListener(this);
  }
  
  public void onClick(InventoryClickEvent e) {}
  
  public void onInteract(PlayerInteractEvent e) {
    HDPlayer HDPlayer = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    assert HDPlayer != null;
    HDPlayer.send("&7&lComing Soon!");
    e.setCancelled(true);
  }
}
