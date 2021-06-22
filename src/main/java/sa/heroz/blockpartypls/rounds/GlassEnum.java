package sa.heroz.blockpartypls.rounds;

import org.bukkit.Material;

enum GlassEnum {
  WHITE(0, "f", "White", Material.STAINED_GLASS),
  ORANGE(1, "6", "Orange", Material.STAINED_GLASS),
  MAGENTA(2, "5", "Magenta", Material.STAINED_GLASS),
  LIGHT_BLUE(3, "b", "Light Blue", Material.STAINED_GLASS),
  YELLOW(4, "e", "Yellow", Material.STAINED_GLASS),
  LIME(5, "a", "Lime", Material.STAINED_GLASS),
  PINK(6, "d", "Pink", Material.STAINED_GLASS),
  GRAY(7, "8", "Gray", Material.STAINED_GLASS),
  SILVER(8, "7", "Silver", Material.STAINED_GLASS),
  CYAN(9, "3", "Cyan", Material.STAINED_GLASS),
  PURPLE(10, "5", "Purple", Material.STAINED_GLASS),
  BLUE(11, "1", "Blue", Material.STAINED_GLASS),
  BROWN(12, "6", "Brown", Material.STAINED_GLASS),
  GREEN(13, "2", "Green", Material.STAINED_GLASS),
  RED(14, "c", "Red", Material.STAINED_GLASS),
  BLACK(15, "0", "Black", Material.STAINED_GLASS);
  
  private int id;
  
  private final String c;
  
  private final String name;
  
  private final String displayname;
  
  private final Material type;
  
  GlassEnum(int id, String c, String name, Material type) {
    this.id = id;
    this.c = c;
    this.name = name;
    this.displayname = "&" + c + name;
    this.type = type;
    GlassEnumStaticClass.BY_ID.put(Integer.valueOf(id), this);
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
  
  public static GlassEnum getById(int id) {
    try {
      return GlassEnumStaticClass.BY_ID.get(Integer.valueOf(id));
    } catch (NullPointerException ex) {
      return null;
    } 
  }
  
  public static int getTotal() {
    return GlassEnumStaticClass.BY_ID.size();
  }
}
