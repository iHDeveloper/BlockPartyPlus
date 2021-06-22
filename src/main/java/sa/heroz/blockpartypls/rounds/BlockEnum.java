package sa.heroz.blockpartypls.rounds;

import org.bukkit.Material;

enum BlockEnum {
  STONE(0, "7", "Stone", Material.STONE),
  COBBLE_STONE(1, "8", "Cobble Stone", Material.COBBLESTONE),
  BEDROCK(2, "0", "Bedrock", Material.BEDROCK);
  
  private int id;
  
  private final String c;
  
  private final String name;
  
  private final String displayname;
  
  private final Material type;
  
  BlockEnum(int id, String c, String name, Material type) {
    this.id = id;
    this.c = c;
    this.name = name;
    this.displayname = "&" + c + name;
    this.type = type;
    BlockEnumStaticClass.BY_ID.put(Integer.valueOf(id), this);
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
  
  public static BlockEnum getById(int id) {
    try {
      return BlockEnumStaticClass.BY_ID.get(Integer.valueOf(id));
    } catch (NullPointerException ex) {
      return null;
    } 
  }
  
  static int getTotal() {
    return BlockEnumStaticClass.BY_ID.size();
  }
}
