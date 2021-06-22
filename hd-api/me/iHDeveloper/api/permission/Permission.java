package me.iHDeveloper.api.permission;

public class Permission {
  private String name;
  
  private String description;
  
  private String permission;
  
  public Permission(String name, String permission) {
    this.name = name;
    this.permission = permission;
  }
  
  public Permission(String name, String permission, String description) {
    this(name, permission);
    setDescription(description);
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public String getDescription() {
    return this.description;
  }
}
