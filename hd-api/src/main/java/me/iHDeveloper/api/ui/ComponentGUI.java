package me.iHDeveloper.api.ui;

import me.iHDeveloper.api.Debug;

public class ComponentGUI implements Component {
  private static int LAST_ID = 0;
  
  private final int id;
  
  private final ComponentType type;
  
  public ComponentGUI(ComponentType type) {
    LAST_ID++;
    this.type = type;
    this.id = LAST_ID;
  }
  
  public int getId() {
    return this.id;
  }
  
  public ComponentType getType() {
    return this.type;
  }
  
  public String toString() {
    return String.format("Component [id:{%s},type:{%s}]", new Object[] { Integer.valueOf(getId()), getType() });
  }
  
  public void typeString() {
    Debug.log(toString(), new Object[0]);
  }
}
