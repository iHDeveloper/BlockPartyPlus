package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import me.iHDeveloper.api.ui.Form;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvents implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClick(InventoryClickEvent e) {
    try {
      if (!(e.getWhoClicked() instanceof Player))
        return; 
      Player player = 
        (Player)e.getWhoClicked();
      Player p = iHDeveloperAPI.getPlayer(player.getName());
      Form f = p.getForm();
      if (f == null)
        return; 
      if (f instanceof me.iHDeveloper.api.player.PlayerMainForm) {
        e.setCancelled(false);
        return;
      } 
      ItemStack item = null;
      if (e.getCursor() == null) {
        item = e.getCurrentItem();
      } else {
        item = e.getCursor();
      } 
      if (item == null) {
        e.setCancelled(true);
        return;
      } 
      if (item.getType().equals(Material.AIR)) {
        e.setCancelled(true);
        return;
      } 
      f.click(e, p, item);
      try {
        Debug.log("Player %s [name='%s',uuid='%s',action='%s',item='%s']", new Object[] { "Inventory Click", p.getName(), p.getUUID(), e.getAction(), e.getCurrentItem().getItemMeta().getDisplayName() });
      } catch (NullPointerException ex) {
        Debug.log("Player %s [name='%s',uuid='%s',action='%s']", new Object[] { "Inventory Click", p.getName(), p.getUUID(), e.getAction() });
      } 
    } catch (NullPointerException nullPointerException) {}
  }
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClose(InventoryCloseEvent e) {
    Player p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    p.setForm(p.getPlayerMainForm());
    Debug.log("Player %s [name='%s',uuid='%s']", new Object[] { "Close Inventory", p.getName(), p.getUUID() });
  }
}
