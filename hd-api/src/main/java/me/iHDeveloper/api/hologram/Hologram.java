package me.iHDeveloper.api.hologram;

import java.util.ArrayList;
import java.util.List;
import me.iHDeveloper.api.player.HDPlayer;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Hologram {
  private final Location location;
  private final List<EntityArmorStand> entities;
  int c;
  private double distance;
  private List<String> list;
  
  public Hologram(Location location, double distance) {
    this.location = location;
    this.distance = distance;
    this.list = new ArrayList<>();
    this.entities = new ArrayList<>();
    this.c = 0;
    load();
  }
  
  private void load() {
    if (this.list.size() <= 0)
      return; 
    int i;
    for (i = this.list.size() - 1; i >= 0; i--) {
      WorldServer world = ((CraftWorld)this.location.getWorld()).getHandle();
      double x = this.location.getX();
      double y = this.location.getY();
      double z = this.location.getZ();
      EntityArmorStand entity = new EntityArmorStand(world, x, y, z);
      entity.setGravity(false);
      entity.setInvisible(true);
      entity.setCustomNameVisible(true);
      entity.setCustomName(this.list.get(i));
      this.location.add(0.0D, this.distance, 0.0D);
      this.entities.add(entity);
      this.c++;
    } 
    for (i = 0; i < this.c; i++)
      this.location.subtract(0.0D, this.distance, 0.0D); 
    this.c = 0;
  }
  
  private void reload() {
    this.entities.clear();
    this.c = 0;
    load();
  }
  
  public void update() {
    beforeUpdate();
    hideAll();
    reload();
    showAll();
    afterUpdate();
  }
  
  public void beforeUpdate() {}
  
  public void afterUpdate() {}
  
  public void addText(String text) {
    text = text.trim();
    this.list.add(text);
  }
  
  public void removeText(int id) {
    this.list.remove(id);
  }
  
  public List<String> getTextList() {
    return this.list;
  }
  
  public void showPlayer(HDPlayer player) {
    showPlayer(player.getPlayer());
  }
  
  public void showPlayer(Player player) {
    for (EntityArmorStand entity : this.entities) {
      World w = player.getWorld();
      World entityWorld = this.location.getWorld();
      if (!w.getName().equalsIgnoreCase(entityWorld.getName()))
        return; 
      PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving((EntityLiving)entity);
      CraftPlayer p = (CraftPlayer)player;
      (p.getHandle()).playerConnection.sendPacket(packet);
    } 
  }
  
  public void showAll() {
    for (Player player : Bukkit.getOnlinePlayers())
      showPlayer(player); 
  }
  
  public void hidePlayer(HDPlayer player) {
    hidePlayer(player.getPlayer());
  }
  
  public void hidePlayer(Player player) {
    for (EntityArmorStand entity : this.entities) {
      PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entity.getId());
      CraftPlayer p = (CraftPlayer)player;
      (p.getHandle()).playerConnection.sendPacket(packet);
    } 
  }
  
  public void hideAll() {
    for (Player player : Bukkit.getOnlinePlayers())
      hidePlayer(player); 
  }
  
  public void setDistance(double distance) {
    this.distance = distance;
  }
  
  public double getDistance() {
    return this.distance;
  }
  
  public Location getLocation() {
    return this.location;
  }
}
