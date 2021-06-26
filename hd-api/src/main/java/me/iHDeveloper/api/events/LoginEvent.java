package me.iHDeveloper.api.events;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.exceptions.APIException;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginEvent implements Listener {

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.LOWEST)
    public void onLogin(PlayerLoginEvent e) {
        try {
            iHDeveloperAPI.loadPlayer(e.getPlayer());
        } catch (APIException ex) {
            ex.printStackTrace();
        }
        HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
        Debug.log("Player %s [name='%s',uuid='%s']", "Join", p.getName(), p.getUUID());
    }

}
