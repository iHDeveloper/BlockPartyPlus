package sa.heroz.game.api;

import sa.heroz.game.enums.GameSettingEnum;
import sa.heroz.game.exceptions.InvaildClassTypeException;

public interface GameAPI {
  void setSettings(GameSettingEnum paramGameSettingEnum, Object paramObject) throws InvaildClassTypeException;
  
  Object getSetting(GameSettingEnum paramGameSettingEnum);
}
