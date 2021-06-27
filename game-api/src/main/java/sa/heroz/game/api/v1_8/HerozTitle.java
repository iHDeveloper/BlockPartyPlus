package sa.heroz.game.api.v1_8;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.util.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sa.heroz.game.HerozGame;

public class HerozTitle {
    public static void show(final HDPlayer p) {
        if (!HerozGame.canUseTitle())
            return;
        int fadeIn = 20;
        int stay = 80;
        int fadeOut = 20;
        String title = iHDeveloperAPI.color("§6§l%s", HerozGame.getName());
        String subTitle = iHDeveloperAPI.color("§7v%s", HerozGame.getVersion());
        p.sendTitle(title, subTitle, fadeIn, stay, fadeOut);
        if (HerozGame.isPrototype()) {
            p.sendSub(37);
            p.send("§8» §e§l%s", HerozGame.getName());
            p.send("§7%s", HerozGame.getDescription());
            p.sendEmpty();
            p.send("§eBy §8»§c %s", HerozGame.getAuthor());
            p.sendSub(37);
        }

        Bukkit.getScheduler().runTaskLater(iHDeveloperAPI.getPlugin(), () -> {
            p.send("§cThis game is under development and may produce issues!");
            p.send("§cPlease report any issue using the following link below:");
            p.sendEmpty();
            p.send("§c§n%s", HerozGame.getIssueTrackerUrl());
            p.sendEmpty();
        }, 5 * TimeUtils.SECONDS);
    }

    public static void clear(HDPlayer p) {
        if (!HerozGame.canUseTitle())
            return;
        p.sendTitle(iHDeveloperAPI.color("&9"), "", 1, 5, 1);
    }

    public static void clearAll() {
        if (!HerozGame.canUseTitle())
            return;
        for (Player player : Bukkit.getOnlinePlayers())
            clear(iHDeveloperAPI.getPlayer(player.getName()));
    }
}
