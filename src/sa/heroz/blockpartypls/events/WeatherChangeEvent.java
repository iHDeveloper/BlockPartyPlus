package sa.heroz.blockpartypls.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class WeatherChangeEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onWeatherChange(org.bukkit.event.weather.WeatherChangeEvent e) {
    if (!e.toWeatherState())
      return; 
    e.getWorld().setTime(0L);
    e.getWorld().setThundering(false);
    e.getWorld().setWeatherDuration(0);
    e.setCancelled(true);
  }
}
