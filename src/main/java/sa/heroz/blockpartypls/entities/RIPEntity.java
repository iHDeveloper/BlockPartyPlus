package sa.heroz.blockpartypls.entities;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Vector3f;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class RIPEntity {
  private static final ArrayList<RIPEntity> entities = new ArrayList<>();
  
  private final HDPlayer player;
  
  private final Location location;
  
  private final EntityArmorStand e;
  
  public static void destroyAllEntities() {
    for (RIPEntity entity : entities) {
      entity.hideAll();
      entity.die();
    } 
  }
  
  public RIPEntity(HDPlayer player, Location location) {
    this.player = player;
    this.location = location;
    this.e = new EntityArmorStand(getCraftWorld().getHandle(), getX(), getY(), getZ());
    this.e.setArms(true);
    this.e.setBasePlate(true);
    this.e.setCustomName(iHDeveloperAPI.color("&c&lRIP &8&l| &f&l%s", getOwnerName()));
    this.e.setCustomNameVisible(true);
    this.e.setGravity(false); // WARNING: the gravity flag is reversed!
    this.e.setSmall(false);
    this.e.setHeadPose(new Vector3f(330.0F, 30.0F, 0.0F));
    this.e.setBodyPose(new Vector3f(0.0F, 0.0F, 0.0F));
    this.e.setLeftArmPose(new Vector3f(-10.0F, 0.0F, 290.0F));
    this.e.setRightArmPose(new Vector3f(-15.0F, 0.0F, 100.0F));
    this.e.setLeftLegPose(new Vector3f(269.0F, 0.0F, -1.0F));
    this.e.setRightLegPose(new Vector3f(270.0F, 0.0F, 1.0F));
    ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
    SkullMeta skull = (SkullMeta)playerHead.getItemMeta();
    skull.setOwner(getOwnerName());
    playerHead.setItemMeta(skull);
    ItemStack chestplateArmorItem = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    ItemStack leggingsArmorItem = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    ItemStack bootsArmorItem = new ItemStack(Material.LEATHER_BOOTS, 1);
    LeatherArmorMeta chestplateArmorMeta = getLeatherArmorMeta(chestplateArmorItem);
    LeatherArmorMeta leggingsArmorMeta = getLeatherArmorMeta(leggingsArmorItem);
    LeatherArmorMeta bootsArmorMeta = getLeatherArmorMeta(bootsArmorItem);
    chestplateArmorItem.setItemMeta(chestplateArmorMeta);
    leggingsArmorItem.setItemMeta(leggingsArmorMeta);
    bootsArmorItem.setItemMeta(bootsArmorMeta);
    getCraftWorld().getHandle().addEntity(this.e);
    ArmorStand armorStand = (ArmorStand)this.e.getBukkitEntity();
    armorStand.setHelmet(playerHead);
    armorStand.setChestplate(chestplateArmorItem);
    armorStand.setLeggings(leggingsArmorItem);
    armorStand.setBoots(bootsArmorItem);
    entities.add(this);
  }
  
  private static final SecureRandom ran = new SecureRandom();
  
  private LeatherArmorMeta getLeatherArmorMeta(ItemStack armor) {
    LeatherArmorMeta meta = (LeatherArmorMeta)armor.getItemMeta();
    meta.setColor(Color.fromRGB(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
    return meta;
  }
  
  public EntityArmorStand getEntity() {
    return this.e;
  }
  
  public void die() {
    getCraftWorld().getHandle().removeEntity(this.e);
  }
  
  public void showAll() {
    for (Player player : Bukkit.getOnlinePlayers())
      showPlayer(player); 
  }
  
  public void showPlayer(Player player) {
    CraftPlayer p = (CraftPlayer)player;
    PlayerConnection connection = (p.getHandle()).playerConnection;
    PacketPlayOutSpawnEntityLiving spawn = new PacketPlayOutSpawnEntityLiving(this.e);
    connection.sendPacket(spawn);
  }
  
  public void hideAll() {
    for (Player player : Bukkit.getOnlinePlayers())
      hidePlayer(player); 
  }
  
  public void hidePlayer(Player player) {
    CraftPlayer p = (CraftPlayer)player;
    PlayerConnection connection = (p.getHandle()).playerConnection;
    PacketPlayOutEntityDestroy destory = new PacketPlayOutEntityDestroy(this.e.getId());
    connection.sendPacket(destory);
  }
  
  public Location getLocation() {
    return this.location;
  }
  
  public World getWorld() {
    return getLocation().getWorld();
  }
  
  public CraftWorld getCraftWorld() {
    return (CraftWorld)getLocation().getWorld();
  }
  
  public double getX() {
    return getLocation().getX();
  }
  
  public double getY() {
    return getLocation().getY();
  }
  
  public double getZ() {
    return getLocation().getZ();
  }
  
  public HDPlayer getPlayer() {
    return this.player;
  }
  
  public UUID getUUID() {
    return getPlayer().getUUID();
  }
  
  public String getOwnerName() {
    return getPlayer().getName();
  }
}
