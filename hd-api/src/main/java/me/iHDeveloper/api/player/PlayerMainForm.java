package me.iHDeveloper.api.player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.ui.ComponentItem;
import me.iHDeveloper.api.ui.Form;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerMainForm extends Form {
  private final PlayerInventory inv;
  
  private final Map<Integer, ComponentItem> items;
  
  public PlayerMainForm(Player player) {
    super(player.getInventory().getSize(), player.getInventory().getTitle());
    this.inv = player.getInventory();
    this.items = new HashMap<>();
  }
  
  public void addItem(int sort, ComponentItem item) {
    this.items.put(Integer.valueOf(sort), item);
    updateInventory();
  }
  
  private void updateInventory() {
    this.inv.clear();
    for (Iterator<Integer> iterator = this.items.keySet().iterator(); iterator.hasNext(); ) {
      int sort = ((Integer)iterator.next()).intValue();
      ComponentItem item = this.items.get(Integer.valueOf(sort));
      if (item != null && item.getItem() != null) {
        this.inv.setItem(sort, item.getItem());
        item.setForm(this);
      } 
    } 
  }
  
  public void interact(PlayerInteractEvent e, Player player, ItemStack itemStack) {
    for (ComponentItem item : getItems()) {
      if (item.getText().equals(itemStack.getItemMeta().getDisplayName())) {
        try {
          item.getListener().onInteract(e);
          Debug.log("Player Main Form Interact [name='%s',uuid='%s',item='%s']", new Object[] { player.getName(), player.getUUID(),
                itemStack.getItemMeta().getDisplayName() });
        } catch (Exception exception) {}
        return;
      } 
    } 
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
