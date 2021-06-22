package me.iHDeveloper.api.spectate.ui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.ArrayList;
import java.util.List;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import me.iHDeveloper.api.ui.ComponentItemEvent;
import me.iHDeveloper.api.ui.Folder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class BackToHubFolder extends Folder implements ComponentItemEvent {
  public BackToHubFolder() {
    super(351, 1, 10);
    setDisplayName("&e&l-|- &f&lBack To Hub &e&l-|-");
    List<String> list = new ArrayList<>();
    list.add(iHDeveloperAPI.color("&7Click to go back to the hub.", new Object[0]));
    setLore(list);
    setNumber(1);
  }
  
  public void onLoad() {
    setListener(this);
  }
  
  public void onClick(InventoryClickEvent e) {}
  
  public void onInteract(PlayerInteractEvent e) {
    Player player = iHDeveloperAPI.getPlayer(e.getPlayer().getName());
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("Connect");
    out.writeUTF("lobby");
    player.getPlayer().sendPluginMessage((Plugin)iHDeveloperAPI.getPlugin(), "BungeeCord", out.toByteArray());
    player.send("&eSending you to lobby...", new Object[0]);
    e.setCancelled(true);
  }
}
