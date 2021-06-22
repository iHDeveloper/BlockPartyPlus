package me.iHDeveloper.api.spectate.ui;

import java.util.ArrayList;
import java.util.List;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.ui.Button;
import me.iHDeveloper.api.ui.ComponentItemEvent;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

class PlayerButton extends Button implements ComponentItemEvent {
  private final HDPlayer p;
  
  public PlayerButton(HDPlayer HDPlayer) {
    super(Material.SKULL, 1);
    this.p = HDPlayer;
    setDisplayName("&f" + getPlayer().getName());
    List<String> list = new ArrayList<>();
    list.add(iHDeveloperAPI.color("&e» Click to spectate"));
    setLore(list);
    SkullMeta meta = (SkullMeta)getItem().getItemMeta();
    meta.setOwner(this.p.getName());
    getItem().setItemMeta((ItemMeta)meta);
  }
  
  public void onLoad() {
    setListener(this);
  }
  
  public void onInteract(PlayerInteractEvent e) {
    HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    assert p != null;
    p.teleport(getPlayer());
    p.send("&eYou are now spectating &f&l%s", getPlayer().getName());
  }
  
  public void onClick(InventoryClickEvent e) {}
  
  public HDPlayer getPlayer() {
    return this.p;
  }
}
