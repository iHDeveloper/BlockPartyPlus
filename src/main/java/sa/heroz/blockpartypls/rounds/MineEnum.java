package sa.heroz.blockpartypls.rounds;

import org.bukkit.Material;

enum MineEnum {
  STONE(0, "7", "Stone", Material.STONE),
  IRON_ORE(1, "f", "Iron Ore", Material.IRON_ORE),
  GOLD_ORE(2, "6", "Gold Ore", Material.GOLD_ORE),
  DIAMOND_ORE(3, "b", "Diamond Ore", Material.DIAMOND_ORE),
  COAL_ORE(4, "8", "Coal Ore", Material.COAL_ORE),
  LAPIS_ORE(5, "1", "Lapis Ore", Material.LAPIS_ORE),
  EMERALD_ORE(6, "a", "Emerald Ore", Material.EMERALD_ORE),
  REDSTONE_ORE(7, "4", "Redstone Ore", Material.REDSTONE_ORE),
  BEDROCK(8, "0", "BedRock", Material.BEDROCK);
  
  private int id;
  
  private final String c;
  
  private final String name;
  
  private final String displayname;
  
  private final Material type;
  
  MineEnum(int id, String c, String name, Material type) {
    this.id = id;
    this.c = c;
    this.name = name;
    this.displayname = "&" + c + name;
    this.type = type;
    MineEnumStaticClass.BY_ID.put(Integer.valueOf(id), this);
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
  
  public static MineEnum getById(int id) {
    try {
      return MineEnumStaticClass.BY_ID.get(Integer.valueOf(id));
    } catch (NullPointerException ex) {
      return null;
    } 
  }
  
  static int getTotal() {
    return MineEnumStaticClass.BY_ID.size();
  }
}
