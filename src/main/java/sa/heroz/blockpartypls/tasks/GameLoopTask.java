package sa.heroz.blockpartypls.tasks;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.tasks.GameTask;
import me.iHDeveloper.api.tasks.GameTaskOptions;
import me.iHDeveloper.api.tasks.GameTaskRunnable;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.rounds.ClayRound;
import sa.heroz.blockpartypls.rounds.DeathMatchRound;
import sa.heroz.blockpartypls.rounds.ForestRound;
import sa.heroz.blockpartypls.rounds.GlassRound;
import sa.heroz.blockpartypls.rounds.KillRound;
import sa.heroz.blockpartypls.rounds.MineRound;
import sa.heroz.blockpartypls.rounds.NetherRound;
import sa.heroz.blockpartypls.rounds.NormalRound;
import sa.heroz.blockpartypls.until.Chat;
import sa.heroz.blockpartypls.until.Floor;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.TempSettings;
import sa.heroz.blockpartypls.until.round.Round;

import java.security.SecureRandom;
import java.util.ArrayList;

import static me.iHDeveloper.api.util.TimeUtils.SECONDS;

@GameTaskOptions(ticks = (13 * SECONDS) + 10) // ticks = 13.5 seconds
public final class GameLoopTask implements GameTask {
    private final Floor floor;
    private final SecureRandom random;

    private int round;
    private Round currentRound;

    public GameLoopTask() {
        this.random = new SecureRandom();
        this.floor = new Floor();
        this.round = 1;
        currentRound = createRound();
        Game.setFloor(floor);
    }

    private Round createRound() {
        if (this.round >= 20)
            return new DeathMatchRound(this.round);

        ArrayList<Round> rounds = new ArrayList<>();
        rounds.add(new NormalRound(this.round));
        rounds.add(new KillRound(this.round));
        rounds.add(new ClayRound(this.round));
        rounds.add(new GlassRound(this.round));
        rounds.add(new MineRound(this.round));
        rounds.add(new NetherRound(this.round));
        rounds.add(new ForestRound(this.round));

        int roundID = this.random.nextInt(rounds.size());
        return rounds.get(roundID);
    }

    @Override
    public void run(int ticks, GameTaskRunnable runnable) {
        // Start Step
        if (ticks == 13 * SECONDS) {
            debug("Starting...");
            currentRound.start();
            return;
        }

        // Coloring the floor
        if (ticks == 12 * SECONDS) {
            debug("Coloring floor...");
            currentRound.color(floor);
            return;
        }

        // Random color
        if (ticks == 11 * SECONDS || ticks == 10 * SECONDS  || ticks == 9 * SECONDS) {
            debug("Generating random color...");
            currentRound.random(Math.abs((11 * SECONDS) - ticks) + 1);
        }

        // Cool down for 3 seconds
        if (ticks == 8 * SECONDS || ticks == 7 * SECONDS || ticks == 6 * SECONDS) {
            debug("Cooling down...");
            return;
        }

        // Clear the floor
        if (ticks == (5 * SECONDS) + 10) {
            debug("Clearing the floor...");
            currentRound.clear(floor);
        }

        // Cool down for 3 seconds
        if (ticks == 5 * SECONDS || ticks == 4 * SECONDS || ticks == 3 * SECONDS) {
            debug("Cooling down...");
            return;
        }

        // Reset the round!
        if (ticks == SECONDS + 4) {
            debug("Resetting the floor...");
            currentRound.reset(floor);
            this.round++;
            for (GamePlayer player : Game.getAllPlayers()) {
                player.getPlayer().getPlayer().setExp(0F);
                player.getPlayer().getPlayer().setLevel(this.round - 1);
                player.givePoints(50, "Still Alive");
            }
            Game.updateAll();
            this.tickDeathMatch();
            currentRound = createRound();
            Game.setCurrentRound(currentRound);
            return;
        }

        if (ticks == 0) {
            runnable.reset();
        }
    }

    private void tickDeathMatch() {
        int round = 20 - Game.getCurrentRound().getId();
        if (round == 0) {
            TempSettings.pvp = false;
            Chat.broadcast("&4&lDeathMatch! &eWatch out from everything.");
        } else if (round == 15 || round == 10 || (round <= 5 && round > 0)) {
            Chat.broadcast("&7[&cDeathMatch&7] &f&l%s&e rounds until to start death match!", round);
        }
    }

    private void debug(String format) {
        Debug.log("[GAME-THREAD (ROUND-%s)] %s", this.round, format);
    }
}
