package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.player.PlayerMainForm;
import me.iHDeveloper.api.ui.Form;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvents implements Listener {
  @SuppressWarnings("unused")
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClick(InventoryClickEvent e) {
    try {
      if (!(e.getWhoClicked() instanceof HDPlayer))
        return; 
      HDPlayer player =
        (HDPlayer)e.getWhoClicked();
      HDPlayer p = iHDeveloperAPI.getPlayer(player.getName());

      if (p == null)
        return;

      Form f = p.getForm();
      if (f == null)
        return; 
      if (f instanceof PlayerMainForm) {
        e.setCancelled(false);
        return;
      } 
      ItemStack item;
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
        Debug.log("Player %s [name='%s',uuid='%s',action='%s',item='%s']", "Inventory Click", p.getName(), p.getUUID(), e.getAction(), e.getCurrentItem().getItemMeta().getDisplayName());
      } catch (NullPointerException ex) {
        Debug.log("Player %s [name='%s',uuid='%s',action='%s']", "Inventory Click", p.getName(), p.getUUID(), e.getAction());
      } 
    } catch (NullPointerException exception) {
      exception.printStackTrace();
    }
  }

  @SuppressWarnings("unused")
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onClose(InventoryCloseEvent e) {
    HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    if (p == null)
      return;

    p.setForm(p.getPlayerMainForm());
    Debug.log("Player %s [name='%s',uuid='%s']", "Close Inventory", p.getName(), p.getUUID());
  }
}
