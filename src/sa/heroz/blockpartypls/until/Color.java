package sa.heroz.blockpartypls.until;

public enum Color {
  WHITE(0, "f", "White"),
  ORANGE(1, "6", "Orange"),
  MAGENTA(2, "5", "Magenta"),
  LIGHT_BLUE(3, "b", "Light Blue"),
  YELLOW(4, "e", "Yellow"),
  LIME(5, "a", "Lime"),
  PINK(6, "d", "Pink"),
  GRAY(7, "8", "Gray"),
  SILVER(8, "7", "Silver"),
  CYAN(9, "3", "Cyan"),
  PURPLE(10, "5", "Purple"),
  BLUE(11, "1", "Blue"),
  BROWN(12, "6", "Brown"),
  GREEN(13, "2", "Green"),
  RED(14, "c", "Red"),
  BLACK(15, "0", "Black");
  
  private int id;
  
  private final String c;
  
  private final String name;
  
  private final String displayname;
  
  Color(int id, String c, String name) {
    this.id = id;
    this.c = c;
    this.name = name;
    this.displayname = "&" + c + name;
    StaticClass.BY_ID.put(Integer.valueOf(id), this);
  }
  
  public int getId() {
    return this.id;
  }
  
  public String getDisplayname() {
    return this.displayname;
  }
  
  public String getChar() {
    return this.c;
  }
  
  public String getName() {
    return this.name;
  }
  
  public static Color getById(int id) {
    try {
      return StaticClass.BY_ID.get(Integer.valueOf(id));
    } catch (NullPointerException ex) {
      return null;
    } 
  }
}
