package sa.heroz.blockpartypls.rounds;

import java.security.SecureRandom;
import java.util.ArrayList;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.HDPlayer;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.Chat;
import sa.heroz.blockpartypls.until.Color;
import sa.heroz.blockpartypls.until.Floor;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;
import sa.heroz.blockpartypls.until.floor.BlockChange;
import sa.heroz.blockpartypls.until.round.Round;
import sa.heroz.blockpartypls.until.round.RoundType;

public class NormalRound extends Round {
  private final ArrayList<Block> blocks = new ArrayList<>();
  
  private int color;
  
  private final SecureRandom ran;
  
  public NormalRound(int id) {
    super(id, "Normal", RoundType.NORMAL);
    this.ran = new SecureRandom();
    setDisplayname("&a&lNormal");
  }
  
  public void start() {
    goToLoading();
  }
  
  public void random(int loop) {
    int c = randomWoolColor();
    Color color = Color.getById(c);
    for (GamePlayer gamePlayer : Game.getAlivePlayers()) {
      HDPlayer p = gamePlayer.getPlayer();
      PlayerInventory inv = p.getInventory();
      inv.clear();
      for (int i = 0; i < 9; i++) {
        ItemStack item = new ItemStack(Material.WOOL, 1, (short)c);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(iHDeveloperAPI.color("&%s-|- &f&l%s &%s-|-", new Object[] { color.getChar(), color.getName(), color.getChar() }));
        item.setItemMeta(meta);
        inv.setItem(i, item);
      } 
    } 
    if (loop == 3) {
      this.color = c;
      Chat.broadcast(iHDeveloperAPI.format(Settings.color, new String[] { "block_name" }, new String[] { iHDeveloperAPI.color("&%s-|- &f&l%s &%s-|-", new Object[] { color.getChar(), color.getName(), color.getChar() }) }), new Object[0]);
    } 
  }
  
  private int randomWoolColor() {
    return this.ran.nextInt(16);
  }
  
  public void color(Floor floor) {
    goToGame();
    floor.drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            if (block.getType().equals(Material.WOOL))
              block.setData((byte)NormalRound.this.randomWoolColor()); 
          }
        });
  }
  
  public void clear(Floor floor) {
    this.blocks.clear();
    floor.drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            if (block.getType().equals(Material.WOOL)) {
              byte data = block.getData();
              if (data != (byte)NormalRound.this.color) {
                NormalRound.this.blocks.add(block);
                block.setType(Material.AIR);
              } 
            } 
          }
        });
  }
  
  public void reset(Floor floor) {
    for (Block block : this.blocks) {
      Bukkit.getScheduler().runTask((Plugin)iHDeveloperAPI.getPlugin(), 
          new Runnable() {
            public void run() {
              block.setType(Material.WOOL);
              block.setData(DyeColor.WHITE.getData());
            }
          });
    } 
    floor.drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            if (block.getType().equals(Material.WOOL))
              block.setData(DyeColor.WHITE.getData()); 
          }
        });
  }
}
