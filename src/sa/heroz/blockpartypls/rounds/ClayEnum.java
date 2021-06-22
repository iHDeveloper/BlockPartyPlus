package sa.heroz.blockpartypls.rounds;

import org.bukkit.Material;

enum ClayEnum {
  WHITE(0, "f", "White", Material.STAINED_CLAY),
  ORANGE(1, "6", "Orange", Material.STAINED_CLAY),
  MAGENTA(2, "5", "Magenta", Material.STAINED_CLAY),
  LIGHT_BLUE(3, "b", "Light Blue", Material.STAINED_CLAY),
  YELLOW(4, "e", "Yellow", Material.STAINED_CLAY),
  LIME(5, "a", "Lime", Material.STAINED_CLAY),
  PINK(6, "d", "Pink", Material.STAINED_CLAY),
  GRAY(7, "8", "Gray", Material.STAINED_CLAY),
  SILVER(8, "7", "Silver", Material.STAINED_CLAY),
  CYAN(9, "3", "Cyan", Material.STAINED_CLAY),
  PURPLE(10, "5", "Purple", Material.STAINED_CLAY),
  BLUE(11, "1", "Blue", Material.STAINED_CLAY),
  BROWN(12, "6", "Brown", Material.STAINED_CLAY),
  GREEN(13, "2", "Green", Material.STAINED_CLAY),
  RED(14, "c", "Red", Material.STAINED_CLAY),
  BLACK(15, "0", "Black", Material.STAINED_CLAY);
  
  private int id;
  
  private final String c;
  
  private final String name;
  
  private final String displayname;
  
  private final Material type;
  
  ClayEnum(int id, String c, String name, Material type) {
    this.id = id;
    this.c = c;
    this.name = name;
    this.displayname = "&" + c + name;
    this.type = type;
    ClayEnumStaticClass.BY_ID.put(Integer.valueOf(id), this);
  }
  
  public String getDisplayname() {
    return this.displayname;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getChar() {
    return this.c;
  }
  
  public int getId() {
    return this.id;
  }
  
  public Material getType() {
    return this.type;
  }
  
  public static ClayEnum getById(int id) {
    try {
      return ClayEnumStaticClass.BY_ID.get(Integer.valueOf(id));
    } catch (NullPointerException ex) {
      return null;
    } 
  }
  
  public static int getTotal() {
    return ClayEnumStaticClass.BY_ID.size();
  }
}
