package sa.heroz.game.api.v1_8;

import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.tasks.GameTask;
import me.iHDeveloper.api.tasks.GameTaskOptions;
import me.iHDeveloper.api.tasks.GameTaskRunnable;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.inventivetalent.bossbar.BossBarAPI;
import sa.heroz.game.HerozGame;

import java.security.SecureRandom;

import static me.iHDeveloper.api.util.TimeUtils.SECONDS;

@GameTaskOptions(ticks = SECONDS + 10)
public class BossBarTask implements GameTask {

    @Override
    public void run(final int ticks, GameTaskRunnable runnable) {
        if (!HerozGame.isStarted()) {
            this.onStop();
            runnable.stop();
            return;
        }

        if (!HerozGame.canUseBossBar()) {
            this.onStop();
            runnable.stop();
            return;
        }

        if (ticks == 30 || ticks == 20 || ticks == 10) {
            this.reloadAll();
        }

        if (ticks == 0) {
            runnable.reset();
        }
    }

    public void onStop() {
        for (Player player : Bukkit.getOnlinePlayers())
            BossBarAPI.removeAllBars(player);
    }

    private void reloadAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            HDPlayer p = iHDeveloperAPI.getPlayer(player.getName());
            assert p != null;
            reload(p);
        }
    }

    private static final SecureRandom ran = new SecureRandom();

    private void reload(HDPlayer p) {
        String playing = "&e&lPLAYING";
        String gameName = "&f&l" + HerozGame.getName();
        String on = "&e&lON";
        String[] colors = { "b", "a", "6", "9", "c", "d" };
        String link = "MC.IHDEVELOPER.ME";
        int color = ran.nextInt(colors.length);
        String text = playing + " " + gameName + " " + on + " &" + colors[color] + "&l" + link;
        BossBarAPI.removeAllBars(p.getPlayer());
        displayBossBar(p, iHDeveloperAPI.color(text));
    }

    private void displayBossBar(HDPlayer p, String text) {
        BossBarAPI.addBar(p.getPlayer(), new TextComponent(text), BossBarAPI.Color.PURPLE, BossBarAPI.Style.NOTCHED_20, 1.0F);
    }
}
