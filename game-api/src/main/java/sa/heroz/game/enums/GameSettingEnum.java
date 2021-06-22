package sa.heroz.game.enums;

public enum GameSettingEnum {
  INFO_NAME(String.class),
  INFO_VERSION(String.class),
  INFO_AUTHOR(String.class),
  INFO_PROTOTYPE(Boolean.class),
  PROPERTIES_BOSSBAR(Boolean.class),
  PROPERTIES_TITLE(Boolean.class);
  
  private Class<?> type;
  
  GameSettingEnum(Class<?> valueType) {
    a(valueType);
  }
  
  private void a(Class<?> type) {
    this.type = type;
  }
  
  public Class<?> getValueType() {
    return this.type;
  }
  
  public boolean check(Class<?> valueType) {
    if (getValueType().equals(valueType))
      return true; 
    return false;
  }
}
