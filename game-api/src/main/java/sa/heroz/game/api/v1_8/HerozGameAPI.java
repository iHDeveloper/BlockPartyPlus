package sa.heroz.game.api.v1_8;

import sa.heroz.game.api.GameAPI;
import sa.heroz.game.enums.GameSettingEnum;
import sa.heroz.game.exceptions.InvaildClassTypeException;

import java.util.HashMap;
import java.util.Map;

public class HerozGameAPI implements GameAPI {
  private final Map<GameSettingEnum, Object> settings;
  
  public HerozGameAPI() {
    this.settings = new HashMap<>();
    a(GameSettingEnum.INFO_NAME, "Heroz Game");
    a(GameSettingEnum.INFO_VERSION, "V0.0_R0");
    a(GameSettingEnum.INFO_AUTHOR, "iHDeveloper");
    a(GameSettingEnum.INFO_PROTOTYPE, false);
    a(GameSettingEnum.PROPERTIES_BOSSBAR, false);
    a(GameSettingEnum.PROPERTIES_TITLE, false);
  }
  
  public void setName(String name) {
    try {
      setSettings(GameSettingEnum.INFO_NAME, name);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    } 
  }

  public void setDescription(String description) {
    try {
      setSettings(GameSettingEnum.INFO_DESCRIPTION, description);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    }
  }
  
  public void setVersion(String version) {
    try {
      setSettings(GameSettingEnum.INFO_VERSION, version);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    } 
  }
  
  public void setAuthor(String author) {
    try {
      setSettings(GameSettingEnum.INFO_AUTHOR, author);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    } 
  }
  
  public void setIsPrototype(boolean prototype) {
    try {
      setSettings(GameSettingEnum.INFO_PROTOTYPE, prototype);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    } 
  }
  
  public void setBossBar(boolean bossbar) {
    try {
      setSettings(GameSettingEnum.PROPERTIES_BOSSBAR, bossbar);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    } 
  }
  
  public void setTitle(boolean title) {
    try {
      setSettings(GameSettingEnum.PROPERTIES_TITLE, title);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    } 
  }

  public void setIssueTrackerUrl(String issueTrackerUrl) {
    try {
      setSettings(GameSettingEnum.INFO_ISSUE_TRACKER_URL, issueTrackerUrl);
    } catch (InvaildClassTypeException e) {
      e.printStackTrace();
    }
  }
  
  public String getName() {
    return (String)getSetting(GameSettingEnum.INFO_NAME);
  }

  public String getDescription() {
    return (String)getSetting(GameSettingEnum.INFO_DESCRIPTION);
  }
  
  public String getVersion() {
    return (String)getSetting(GameSettingEnum.INFO_VERSION);
  }
  
  public String getAuthor() {
    return (String)getSetting(GameSettingEnum.INFO_AUTHOR);
  }

  public String getIssueTrackerUrl() {
    return (String) getSetting(GameSettingEnum.INFO_ISSUE_TRACKER_URL);
  }
  
  public boolean isPrototype() {
    return (Boolean) getSetting(GameSettingEnum.INFO_PROTOTYPE);
  }
  
  public boolean canUseBossBar() {
    return (Boolean) getSetting(GameSettingEnum.PROPERTIES_BOSSBAR);
  }
  
  public boolean canUseTitle() {
    return (Boolean) getSetting(GameSettingEnum.PROPERTIES_TITLE);
  }
  
  public void setSettings(GameSettingEnum setting, Object value) throws InvaildClassTypeException {
    if (!setting.check(value.getClass()))
      throw new InvaildClassTypeException(""); 
    a(setting, value);
  }
  
  public Object getSetting(GameSettingEnum setting) {
    return this.settings.get(setting);
  }
  
  private void a(GameSettingEnum a, Object b) {
    this.settings.put(a, b);
  }
}
