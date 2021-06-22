package me.iHDeveloper.api.spectate.ui;

import java.util.ArrayList;
import java.util.List;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import me.iHDeveloper.api.ui.Button;
import me.iHDeveloper.api.ui.ComponentItemEvent;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

class PlayerButton extends Button implements ComponentItemEvent {
  private final Player p;
  
  public PlayerButton(Player player) {
    super(Material.SKULL, 1);
    this.p = player;
    setDisplayName("&f" + getPlayer().getName());
    List<String> list = new ArrayList<>();
    list.add(iHDeveloperAPI.color("&e» Click to spectate", new Object[0]));
    setLore(list);
    SkullMeta meta = (SkullMeta)getItem().getItemMeta();
    meta.setOwner(this.p.getName());
    getItem().setItemMeta((ItemMeta)meta);
  }
  
  public void onLoad() {
    setListener(this);
  }
  
  public void onInteract(PlayerInteractEvent e) {
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    p.teleport(getPlayer());
    p.send("&eYou are now spectating &f&l%s", new Object[] { getPlayer().getName() });
  }
  
  public void onClick(InventoryClickEvent e) {}
  
  public Player getPlayer() {
    return this.p;
  }
}
