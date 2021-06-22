package me.iHDeveloper.api.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.configuration.Configuration;

public class PermissionsManager {
  private static Map<String, Permission> permissions = new HashMap<>();
  
  public static void load() {
    permissions.clear();
    List<String> list = iHDeveloperAPI.getConfig().getStringList("permissions.list");
    if (list != null) {
      Debug.log("------ Loading permissions from config ------", new Object[0]);
      Configuration c = iHDeveloperAPI.getConfig();
      for (String id : list) {
        String pMain = "permissions." + id + ".";
        String pName = String.valueOf(pMain) + "name";
        String pPermission = String.valueOf(pMain) + "permission";
        String pDescription = String.valueOf(pMain) + "description";
        String name = c.getString(pName);
        String permission = c.getString(pPermission);
        String description = c.getString(pDescription);
        Permission permissionClass = new Permission(name, permission, description);
        permissions.put(name, permissionClass);
        Debug.log("- Load permission >> " + name + " | " + permission, new Object[0]);
      } 
      Debug.log("------ Loaded permissions from config! ------", new Object[0]);
    } else {
      Debug.log("------ Not found any permission ------", new Object[0]);
    } 
  }
  
  public static Permission get(String name) {
    return permissions.get(name);
  }
}
