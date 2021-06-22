package sa.heroz.blockpartypls.rounds;

import org.bukkit.Material;

enum ForestEnum {
  GRASS(0, "2", "Grass", Material.GRASS),
  DIRT(1, "6", "Dirt", Material.DIRT),
  COARSE_DIRT(2, "6", "Coarse Dirt", Material.DIRT, (byte)1),
  PODZOL(3, "6", "Podzol", Material.DIRT, (byte)2),
  OKA_WOOD(4, "e", "Oka Wood", Material.LOG),
  SPRUCE_WOOD(5, "6", "Spruce Wood", Material.LOG, (byte)1),
  BIRCH_WOOD(6, "f", "Birch Wood", Material.LOG, (byte)2),
  JUNCLE_WOOD(7, "7", "Juncle Wood", Material.LOG, (byte)3);
  
  private int id;
  
  private final String c;
  
  private final String name;
  
  private final String displayname;
  
  private final Material type;
  
  private final byte data;
  
  ForestEnum(int id, String c, String name, Material type, byte data) {
    this.id = id;
    this.c = c;
    this.name = name;
    this.displayname = "&" + c + name;
    this.type = type;
    this.data = data;
    ForestEnumStaticClass.BY_ID.put(Integer.valueOf(id), this);
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
  
  public byte getData() {
    return this.data;
  }
  
  public static ForestEnum getById(int id) {
    try {
      return ForestEnumStaticClass.BY_ID.get(Integer.valueOf(id));
    } catch (NullPointerException ex) {
      return null;
    } 
  }
  
  static int getTotal() {
    return ForestEnumStaticClass.BY_ID.size();
  }
}
