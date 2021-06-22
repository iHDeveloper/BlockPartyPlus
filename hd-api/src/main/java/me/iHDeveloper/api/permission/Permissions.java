package me.iHDeveloper.api.permission;

public enum Permissions {
  ;

  private final String name;
  
  private final Permission permission;
  
  Permissions(String name) {
    this.name = name;
    this.permission = PermissionsManager.get(name);
  }
  
  public Permission get() {
    return this.permission;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getPermission() {
    return get().getPermission();
  }
}
