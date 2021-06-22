package me.iHDeveloper.api.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Form extends ComponentGUI {
  private final Inventory inv;
  
  private final Map<Integer, ComponentItem> items;
  
  public Form(int size, String title) {
    super(ComponentType.FORM);
    this.inv = Bukkit.createInventory(null, size, iHDeveloperAPI.color(title, new Object[0]));
    this.items = new HashMap<>();
    typeString();
  }
  
  public void addItem(int sort, ComponentItem item) {
    this.items.put(Integer.valueOf(sort), item);
    updateInventroy();
  }
  
  private void updateInventroy() {
    for (Iterator<Integer> iterator = this.items.keySet().iterator(); iterator.hasNext(); ) {
      int sort = ((Integer)iterator.next()).intValue();
      ComponentItem item = this.items.get(Integer.valueOf(sort));
      if (item != null && item.getItem() != null) {
        this.inv.setItem(sort, item.getItem());
        item.setForm(this);
      } 
    } 
  }
  
  public void show(final HDPlayer HDPlayer) {
    final Form form = this;
    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)iHDeveloperAPI.getPlugin(), new Runnable() {
          public void run() {
            HDPlayer.openInv(Form.this.inv);
            HDPlayer.setForm(form);
          }
        },  10L);
  }
  
  public void clear() {
    this.items.clear();
    getInventory().clear();
  }
  
  public void click(InventoryClickEvent e, HDPlayer HDPlayer, ItemStack itemStack) {
    for (ComponentItem item : getItems()) {
      if (item.getItem().equals(e.getCursor())) {
        try {
          item.getListener().onClick(e);
        } catch (Exception exception) {}
        return;
      } 
    } 
  }
  
  public void interact(PlayerInteractEvent e, HDPlayer HDPlayer, ItemStack itemStack) {
    for (ComponentItem item : getItems()) {
      if (item.getItem().equals(itemStack)) {
        try {
          item.getListener().onInteract(e);
        } catch (Exception exception) {}
        return;
      } 
    } 
  }
  
  public Inventory getInventory() {
    return this.inv;
  }
  
  public Collection<ComponentItem> getItems() {
    return this.items.values();
  }
  
  public int getSolt(ItemStack item) {
    for (Iterator<Integer> iterator = this.items.keySet().iterator(); iterator.hasNext(); ) {
      int solt = ((Integer)iterator.next()).intValue();
      ComponentItem i = this.items.get(Integer.valueOf(solt));
      ItemStack itemStack = i.getItem();
      if (itemStack.equals(item))
        return solt; 
    } 
    return -1;
  }
}
