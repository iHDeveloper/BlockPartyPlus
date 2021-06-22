package me.iHDeveloper.api.npc;

import com.mojang.authlib.GameProfile;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import me.iHDeveloper.api.hologram.Hologram;
import me.iHDeveloper.api.player.HDPlayer;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;
import net.minecraft.server.v1_8_R3.WorldSettings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NPC {
  private static final Map<String, NPC> npcs = new HashMap<>();

  private final Entity entity;
  private final Hologram hologram;
  private final String name;

  public static Set<String> getAllNPCsName() {
    return npcs.keySet();
  }
  
  public static String shuffleName() {
    return UUID.randomUUID().toString().substring(0, 5);
  }
  
  public NPC(Location location, UUID uuid) {
    this.name = shuffleName();
    MinecraftServer server = ((CraftServer)Bukkit.getServer()).getServer();
    WorldServer world = ((CraftWorld)location.getWorld()).getHandle();
    GameProfile profile = new GameProfile(UUID.randomUUID(), this.name);
    PlayerInteractManager manager = new PlayerInteractManager((World)world);
    this.entity = new Entity(server, world, profile, manager);
    this.entity.playerInteractManager.setGameMode(WorldSettings.EnumGamemode.CREATIVE);
    this.entity.teleportTo(location, false);
    Location l = location.add(0.0D, 0.75D, 0.0D);
    this.hologram = new Hologram(l, 0.5D);
    this.hologram.addText(this.name);
    this.hologram.update();
    npcs.put(this.name, this);
  }
  
  public Hologram getHologram() {
    return this.hologram;
  }
  
  public Entity getEntity() {
    return this.entity;
  }
  
  public void showPlayer(HDPlayer player) {
    showPlayer(player.getPlayer());
  }
  
  public void showPlayer(Player player) {
    PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn((EntityHuman)this.entity);
    PacketPlayOutPlayerInfo info = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, this.entity);
    CraftPlayer p = (CraftPlayer)player;
    PlayerConnection connection = (p.getHandle()).playerConnection;
    connection.sendPacket(spawn);
    connection.sendPacket(info);
  }
  
  public void showAll() {
    for (Player player : Bukkit.getOnlinePlayers())
      showPlayer(player); 
  }
  
  public void hidePlayer(HDPlayer player) {
    hidePlayer(player.getPlayer());
  }
  
  public void hidePlayer(Player player) {
    PacketPlayOutEntityDestroy destory = new PacketPlayOutEntityDestroy(this.entity.getId());
    CraftPlayer p = (CraftPlayer)player;
    PlayerConnection connection = (p.getHandle()).playerConnection;
    connection.sendPacket(destory);
  }
  
  public void hideAll() {
    for (Player player : Bukkit.getOnlinePlayers())
      hidePlayer(player); 
  }

  @Override
  public String toString() {
    return "NPC{" +
            "entity=" + entity +
            ", hologram=" + hologram +
            ", name='" + name + '\'' +
            '}';
  }
}
