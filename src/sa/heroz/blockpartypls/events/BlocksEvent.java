package sa.heroz.blockpartypls.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import sa.heroz.blockpartypls.until.TempSettings;

public class BlocksEvent implements Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onBlockBreak(BlockBreakEvent e) {
    if (TempSettings.build && e.getPlayer().isOp()) {
      e.setCancelled(false);
      return;
    } 
    e.setCancelled(true);
  }
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onBlockBurn(BlockBurnEvent e) {
    e.setCancelled(true);
  }
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onBlockExplode(BlockExplodeEvent e) {
    e.setCancelled(true);
  }
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlaceBlock(BlockPlaceEvent e) {
    if (TempSettings.build && e.getPlayer().isOp()) {
      e.setCancelled(false);
      return;
    } 
    e.setCancelled(true);
  }
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerDropItem(PlayerDropItemEvent e) {
    e.setCancelled(true);
  }
  
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerPickupItem(PlayerPickupItemEvent e) {
    e.setCancelled(true);
  }
}
