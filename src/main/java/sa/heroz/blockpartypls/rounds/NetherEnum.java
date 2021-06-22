package sa.heroz.blockpartypls.rounds;

import org.bukkit.Material;

enum NetherEnum {
  NETHERRACK(0, "4", "Netherrack", Material.NETHERRACK),
  NETHER_QUARTZ_ORE(1, "f", "Nether Quartz Ore", Material.QUARTZ_ORE),
  SOUL_SAND(2, "8", "Soul Sand", Material.SOUL_SAND),
  NETHERBRICK(3, "0", "Nether Brick", Material.NETHER_BRICK),
  GLOWSTONE(4, "e", "Glowstone", Material.GLOWSTONE);
  
  private int id;
  
  private final String c;
  
  private final String name;
  
  private final String displayname;
  
  private final Material type;
  
  NetherEnum(int id, String c, String name, Material type) {
    this.id = id;
    this.c = c;
    this.name = name;
    this.displayname = "&" + c + name;
    this.type = type;
    NetherEnumStaticClass.BY_ID.put(Integer.valueOf(id), this);
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
  
  public static NetherEnum getById(int id) {
    try {
      return NetherEnumStaticClass.BY_ID.get(Integer.valueOf(id));
    } catch (NullPointerException ex) {
      return null;
    } 
  }
  
  static int getTotal() {
    return NetherEnumStaticClass.BY_ID.size();
  }
}
