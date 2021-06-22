package sa.heroz.blockpartypls.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import me.iHDeveloper.api.spectate.SpectateSystem;
import me.iHDeveloper.api.thread.GameThreadManager;
import org.bukkit.GameMode;
import org.bukkit.Location;
import sa.heroz.blockpartypls.entities.RIPEntity;
import sa.heroz.blockpartypls.holograms.StatsHologram;
import sa.heroz.blockpartypls.holograms.TopKillsHologram;
import sa.heroz.blockpartypls.holograms.TopPointsHologram;
import sa.heroz.blockpartypls.scoreboard.GameScoreboard;
import sa.heroz.blockpartypls.threads.GameThread;
import sa.heroz.blockpartypls.threads.StartThread;
import sa.heroz.blockpartypls.until.Chat;
import sa.heroz.blockpartypls.until.Console;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;
import sa.heroz.game.HerozGame;

public class Game {
  private static GameState state = GameState.LOADING;
  
  private static Map<UUID, GamePlayer> base;
  
  private static Map<UUID, GamePlayer> players;
  
  private static Map<UUID, GamePlayer> spectators;
  
  private static Map<UUID, StatsHologram> hologramsStats;
  
  private static TopKillsHologram topKillsHologram;
  
  private static TopPointsHologram topPointsHologram;
  
  private static GamePlayer winner;
  
  public static void setState(GameState state) {
    Game.state = state;
  }
  
  public static void host() {
    state = GameState.WAITING;
    base = new HashMap<>();
    players = new HashMap<>();
    spectators = new HashMap<>();
    hologramsStats = new HashMap<>();
    topKillsHologram = new TopKillsHologram();
    topPointsHologram = new TopPointsHologram();
    HerozGame.start();
    Console.log("Waiting for player [WAITING STATE]");
  }
  
  public static void add(HDPlayer HDPlayer) {
    Console.log("%s joined", HDPlayer.getName());
    GamePlayer p = new GamePlayer(HDPlayer);
    base.put(HDPlayer.getUUID(), p);
    players.put(HDPlayer.getUUID(), base.get(HDPlayer.getUUID()));
    updateAll();
    Chat.broadcast(iHDeveloperAPI.format(Settings.join, 
          new String[] { "displayname", "cplayers", "mplayers" }, new String[] { HDPlayer.getDisplayName(),
            "" + getAlivePlayers().size(),
            "" + Settings.maxPlayers }));
    Console.log("%s joined", HDPlayer.getName());
    if (getState() == GameState.WAITING) {
      HDPlayer.getPlayer().setLevel(0);
      HDPlayer.getPlayer().setExp(0.0F);
      if (Settings.lobby != null) {
        HDPlayer.getPlayer().teleport(Settings.lobby);
      } else {
        HDPlayer.sendError("Not found the lobby location");
      } 
      if (getAlivePlayers().size() >= Settings.maxPlayers)
        start(); 
    } 
  }
  
  public static void eliminate(HDPlayer HDPlayer, Location fallLocation) {
    if (!players.containsKey(HDPlayer.getUUID()))
      return; 
    players.remove(HDPlayer.getUUID());
    spectators.put(HDPlayer.getUUID(), base.get(HDPlayer.getUUID()));
    String reason;
    HDPlayer damager = GameEvents.getDamager(HDPlayer.getUUID());
    if (damager == null) {
      reason = "Fall down";
    } else {
      GamePlayer gp = players.get(damager.getUUID());
      reason = "Killed by " + damager.getName();
      gp.giveKill("Kill " + HDPlayer.getName());
      gp.givePoints(150, "Kill " + HDPlayer.getName());
    } 
    HDPlayer.setGameMode(GameMode.SPECTATOR);
    HDPlayer.teleport(Settings.lobby);
    Chat.broadcast(iHDeveloperAPI.format(Settings.eliminated, 
          new String[] { "displayname", "round", "reason" }, new String[] { HDPlayer.getDisplayName(), "" + thread.getRound().getId(), reason }));
    for (GamePlayer gamePlayer : getAlivePlayers())
      gamePlayer.givePoints(25, "Someone died"); 
    RIPEntity entity = new RIPEntity(HDPlayer, fallLocation);
    entity.showAll();
    if (getAlivePlayers().size() <= 1) {
      setState(GameState.FINISH);
      GamePlayer winner = (new LinkedList<>(getAlivePlayers())).get(0);
      Game.winner = winner;
      Chat.broadcast(iHDeveloperAPI.format(Settings.win, 
            new String[] { "displayname" }, new String[] { winner.getPlayer().getDisplayName() }));
      for (GamePlayer gamePlayer : getAlivePlayers())
        players.remove(gamePlayer.getPlayer().getUUID()); 
      for (GamePlayer gamePlayer : getAllPlayers()) {
        HDPlayer p = gamePlayer.getPlayer();
        p.clearInv();
        p.setGameMode(GameMode.ADVENTURE);
        p.getPlayer().setExp(0.0F);
        p.getPlayer().setLevel(0);
        try {
          p.getPlayer().teleport(Settings.lobby);
        } catch (NullPointerException ex) {
          p.sendError("Not found the lobby location");
        } 
        SpectateSystem.reset();
      } 
    } 
    updateAll();
  }
  
  public static void onQuit(HDPlayer HDPlayer) {
    if (!players.containsKey(HDPlayer.getUUID()))
      return; 
    spectators.remove(HDPlayer.getUUID());
    players.remove(HDPlayer.getUUID());
    base.remove(HDPlayer.getUUID());
    StatsHologram h = hologramsStats.get(HDPlayer.getUUID());
    h.hideAll();
    hologramsStats.remove(HDPlayer.getUUID());
    Chat.broadcast(iHDeveloperAPI.format(Settings.left, 
          new String[] { "displayname" }, new String[] { HDPlayer.getDisplayName() }));
    if (getState() != GameState.STARTING)
      return; 
    if (players.size() < Settings.maxPlayers / 2) {
      Chat.broadcast(iHDeveloperAPI.format(Settings.no_enough, 
            new String[0], 
            new String[0]));
      try {
        startGTM.stop();
      } catch (NullPointerException exception) {
        exception.printStackTrace();
      }
      setState(GameState.WAITING);
    } 
    updateAll();
  }
  
  public static void startByAdmin(HDPlayer HDPlayer) {
    start();
    Chat.broadcast(iHDeveloperAPI.format(Settings.start_by_admin, 
          new String[] { "displayname" }, new String[] { HDPlayer.getDisplayName() }));
  }
  
  public static GameThreadManager startGTM = null;
  
  public static void start() {
    setState(GameState.STARTING);
    updateAll();
    StartThread start = new StartThread();
    startGTM = new GameThreadManager(start);
    startGTM.start();
  }

  public static void startGame() {
    setState(GameState.IN_GAME);
    updateAll();
  }
  
  private static GameThread thread = null;
  
  public static void play() {
    HerozGame.stop();
    setState(GameState.IN_GAME);
    thread = new GameThread();
    GameThreadManager gtm = new GameThreadManager(thread);
    gtm.start();
    updateAll();
  }
  
  public static GameThread getGameThread() {
    return thread;
  }
  
  public static void updateAll() {
    for (GamePlayer p : getAllPlayers()) {
      GameScoreboard sb = p.getScoreboard();
      try {
        sb.update();
      } catch (NullPointerException exception) {
        exception.printStackTrace();
      }
      try {
        StatsHologram stats = hologramsStats.get(p.getPlayer().getUUID());
        stats.update();
      } catch (NullPointerException ex) {
        StatsHologram stats = new StatsHologram(p);
        stats.update();
        hologramsStats.put(p.getPlayer().getUUID(), stats);
      } 
    } 
    topKillsHologram.update();
    topPointsHologram.update();
  }
  
  public static Collection<GamePlayer> getAllPlayers() {
    return base.values();
  }
  
  public static Collection<GamePlayer> getAlivePlayers() {
    return players.values();
  }
  
  public static Collection<GamePlayer> getSpectatorsPlayers() {
    return spectators.values();
  }
  
  public static GamePlayer getWinner() {
    return winner;
  }
  
  public static GamePlayerRole getPlayerRole(GamePlayer player) {
    HDPlayer p = player.getPlayer();
    if (getState() == GameState.FINISH)
      try {
        if (p.getUUID().equals(winner.getPlayer().getUUID()))
          return GamePlayerRole.WINNER; 
        return GamePlayerRole.LOSER;
      } catch (NullPointerException ex) {
        return GamePlayerRole.LOSER;
      }  
    if (players.containsKey(p.getUUID()))
      return GamePlayerRole.PLAYER; 
    return GamePlayerRole.DEAD;
  }
  
  public static GamePlayer getPlayer(UUID uuid) {
    return base.get(uuid);
  }
  
  public static GameState getState() {
    return state;
  }
}
