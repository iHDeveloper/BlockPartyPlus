package me.iHDeveloper.api.ui;

import org.bukkit.Material;

public class Button extends ComponentItem {
  private static final ComponentType type = ComponentType.BUTTON;
  
  public Button(Material m) {
    super(type, m.getId(), 1, 0);
  }
  
  public Button(Material m, int amount) {
    super(type, m.getId(), amount, 0);
  }
  
  public Button(Material m, int amount, int data) {
    super(type, m.getId(), amount, data);
  }
  
  public Button(int id) {
    super(type, id, 1, 0);
  }
  
  public Button(int id, int amount) {
    super(type, id, amount, 0);
  }
  
  public Button(int id, int amount, int data) {
    super(type, id, amount, data);
  }
  
  public void onLoad() {
    setListener(new ComponentItemEventNull());
  }
}
