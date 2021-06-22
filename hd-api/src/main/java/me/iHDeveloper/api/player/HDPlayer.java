package me.iHDeveloper.api.player;

import java.util.UUID;
import me.iHDeveloper.api.exceptions.APIException;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.ui.ComponentItem;
import me.iHDeveloper.api.ui.Form;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class HDPlayer {
  private static int LAST_ID = 0;
  
  private final int id;
  
  private final org.bukkit.entity.Player player;
  
  private UUID uuid;
  
  private Form form;
  
  private PlayerMainForm mainForm;
  
  public HDPlayer(org.bukkit.entity.Player player) throws APIException {
    if (player == null)
      throw new APIException("(Object='Player') [ex='bukkit-player-null']"); 
    this.player = player;
    this.id = ++LAST_ID;
    this.uuid = iHDeveloperAPI.getUUIDDatabase().get(player);
    this.mainForm = new PlayerMainForm(this);
  }
  
  public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
    IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + iHDeveloperAPI.color(title, new Object[0]) + "\"}");
    IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + iHDeveloperAPI.color(subtitle, new Object[0]) + "\"}");
    PacketPlayOutTitle length = new PacketPlayOutTitle(fadeIn, stay, fadeOut);
    PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
    PacketPlayOutTitle packetSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubTitle);
    PlayerConnection connection = (((CraftPlayer)this.player.getPlayer()).getHandle()).playerConnection;
    connection.sendPacket((Packet)packetTitle);
    connection.sendPacket((Packet)packetSubTitle);
    connection.sendPacket((Packet)length);
  }
  
  public void send(String format, Object... args) {
    getPlayer().sendMessage(iHDeveloperAPI.color("%s %s", new Object[] { iHDeveloperAPI.getPrefix(), String.format(format, args).trim() }));
  }
  
  public void sendNoPrefix(String format, Object... args) {
    getPlayer().sendMessage(iHDeveloperAPI.color("%s", new Object[] { String.format(format, args) }));
  }
  
  public void sendSub() {
    sendSub(28);
  }
  
  public void sendSub(int i) {
    String msg = "&e&l";
    for (int j = 0; j < i; j++)
      msg = String.valueOf(msg) + "-"; 
    send(msg, new Object[0]);
  }
  
  public void sendEmpty() {
    getPlayer().sendMessage(iHDeveloperAPI.color("&9", new Object[0]));
  }
  
  public void sendError(String message, Object... args) {
    send("&c&lERR: &7" + message, args);
  }
  
  public void teleport(HDPlayer player) {
    teleport(player.getLocation());
  }
  
  public void teleport(Location location) {
    getPlayer().teleport(location);
  }
  
  public void addItem(int sort, ComponentItem item) {
    getPlayerMainForm().addItem(sort, item);
  }
  
  public void openInv(Inventory inventory) {
    getPlayer().openInventory(inventory);
  }
  
  public void clearInv() {
    PlayerInventory inv = getInventory();
    inv.setHelmet(null);
    inv.setChestplate(null);
    inv.setLeggings(null);
    inv.setBoots(null);
    inv.clear();
  }
  
  public synchronized void setUUID(UUID uuid) {
    this.uuid = uuid;
  }
  
  public synchronized void setForm(Form form) {
    this.form = form;
  }
  
  public void setGameMode(GameMode mode) {
    getPlayer().setGameMode(mode);
  }
  
  public int getId() {
    return this.id;
  }
  
  public org.bukkit.entity.Player getPlayer() {
    return this.player;
  }
  
  public UUID getUUID() {
    return this.uuid;
  }
  
  public PlayerInventory getInventory() {
    return getPlayer().getInventory();
  }
  
  public Form getForm() {
    return this.form;
  }
  
  public String getName() {
    String result = getPlayer().getName();
    if (result == null)
      return "Anonymous";
    return result;
  }
  
  public String getDisplayName() {
    return iHDeveloperAPI.color(getPlayer().getDisplayName(), new Object[0]);
  }
  
  public PlayerMainForm getPlayerMainForm() {
    return this.mainForm;
  }
  
  public Location getLocation() {
    return getPlayer().getLocation();
  }
  
  public GameMode getGameMode() {
    return getPlayer().getGameMode();
  }
}
