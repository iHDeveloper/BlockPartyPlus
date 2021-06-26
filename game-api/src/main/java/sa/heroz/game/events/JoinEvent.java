package sa.heroz.game.events;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sa.heroz.game.api.v1_8.HerozTitle;

public class JoinEvent implements Listener {

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        HDPlayer p = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
        HerozTitle.show(p);
    }

}
