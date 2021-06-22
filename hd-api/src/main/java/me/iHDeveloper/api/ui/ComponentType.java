package me.iHDeveloper.api.ui;

public enum ComponentType {
  COMPONENT("COMPONENT"),
  FORM("FORM"),
  FOLDER("FOLDER"),
  ITEM_NONE("ITEM_NONE"),
  BUTTON("BUTTON");
  
  private final String name;
  
  ComponentType(String name) {
    this.name = name;
  }
  
  public String toString() {
    return this.name;
  }
}
