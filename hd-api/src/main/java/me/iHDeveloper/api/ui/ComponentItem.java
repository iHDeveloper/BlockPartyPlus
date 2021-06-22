package me.iHDeveloper.api.ui;

import java.util.ArrayList;
import java.util.List;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class ComponentItem extends ComponentGUI implements Component {
  private final ItemStack item;
  
  private ComponentItemEvent listener;
  
  private Form form;
  
  public ComponentItem(ComponentType type, Material m) {
    this(type, m.getId(), 1, 0);
  }
  
  public ComponentItem(ComponentType type, Material m, int amount) {
    this(type, m.getId(), amount, 0);
  }
  
  public ComponentItem(ComponentType type, Material m, int amount, int data) {
    this(type, m.getId(), amount, data);
  }
  
  public ComponentItem(ComponentType type, int id) {
    this(type, id, 1, 0);
  }
  
  public ComponentItem(ComponentType type, int id, int amount) {
    this(type, id, amount, 0);
  }
  
  public ComponentItem(ComponentType type, int id, int amount, int data) {
    super(type);
    this.item = new ItemStack(id, amount, (short)data);
    this.listener = null;
    onLoad();
    typeString();
  }
  
  public abstract void onLoad();
  
  public void setListener(ComponentItemEvent listener) {
    this.listener = listener;
  }
  
  public void setDisplayName(String name) {
    ItemMeta meta = getItem().getItemMeta();
    meta.setDisplayName(iHDeveloperAPI.color(name, new Object[0]));
    getItem().setItemMeta(meta);
  }
  
  public void setLore(List<String> lore) {
    ItemMeta meta = getItem().getItemMeta();
    List<String> list = new ArrayList<>();
    for (String desc : lore)
      list.add(iHDeveloperAPI.color(desc, new Object[0])); 
    meta.setLore(list);
    getItem().setItemMeta(meta);
  }
  
  public ComponentItemEvent getListener() {
    return this.listener;
  }
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public int getAmount() {
    return getItem().getAmount();
  }
  
  public ItemMeta getMeta() {
    return getItem().getItemMeta();
  }
  
  public String getText() {
    return getMeta().getDisplayName();
  }
  
  public int getId() {
    return super.getId();
  }
  
  public ComponentType getType() {
    return super.getType();
  }
  
  public String toString() {
    return String.format("Component [id:{%s},type:{%s}]", new Object[] { Integer.valueOf(getId()), getType() });
  }
  
  public void typeString() {
    System.out.println(toString());
  }
  
  public synchronized void setForm(Form form) {
    this.form = form;
  }
  
  public Form getForm() {
    return this.form;
  }
}
