package sa.heroz.blockpartypls.rounds;

import java.security.SecureRandom;
import java.util.ArrayList;
import me.iHDeveloper.api.iHDeveloperAPI;
import me.iHDeveloper.api.player.Player;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import sa.heroz.blockpartypls.game.Game;
import sa.heroz.blockpartypls.until.Chat;
import sa.heroz.blockpartypls.until.Floor;
import sa.heroz.blockpartypls.until.GamePlayer;
import sa.heroz.blockpartypls.until.Settings;
import sa.heroz.blockpartypls.until.floor.BlockChange;
import sa.heroz.blockpartypls.until.round.Round;
import sa.heroz.blockpartypls.until.round.RoundType;

public class NetherRound extends Round {
  private int i = 0;
  
  private ArrayList<Block> blocks = new ArrayList<>();
  
  private final SecureRandom ran;
  
  public NetherRound(int id) {
    super(id, "Nether", RoundType.NETHER);
    this.ran = new SecureRandom();
    setDisplayname("&4&lNether");
  }
  
  public void start() {
    goToLoading();
  }
  
  public void random(int loop) {
    int c = randomBlock();
    NetherEnum b = NetherEnum.getById(c);
    for (GamePlayer gamePlayer : Game.getAlivePlayers()) {
      Player player = gamePlayer.getPlayer();
      PlayerInventory inv = player.getInventory();
      inv.clear();
      for (int i = 0; i < 9; i++) {
        ItemStack item = new ItemStack(b.getType(), 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(iHDeveloperAPI.color("&%s-|- &f&l%s &%s-|-", new Object[] { b.getChar(), b.getName(), b.getChar() }));
        item.setItemMeta(meta);
        inv.setItem(i, item);
      } 
    } 
    if (loop == 3) {
      this.i = c;
      Chat.broadcast(iHDeveloperAPI.format(Settings.color, new String[] { "block_name" }, new String[] { iHDeveloperAPI.color("&%s-|- &f&l%s &%s-|-", new Object[] { b.getChar(), b.getName(), b.getChar() }) }), new Object[0]);
    } 
  }
  
  private int randomBlock() {
    return this.ran.nextInt(NetherEnum.getTotal());
  }
  
  public void color(Floor floor) {
    goToGame();
    floor.drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            if (block.getType().equals(Material.WOOL)) {
              NetherEnum b = NetherEnum.getById(NetherRound.this.randomBlock());
              block.setType(b.getType());
            } 
          }
        });
  }
  
  public void clear(Floor floor) {
    this.blocks.clear();
    floor.drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            if (block.getType().equals(Material.AIR))
              return; 
            NetherEnum b = NetherEnum.getById(NetherRound.this.i);
            if (!block.getType().equals(b.getType())) {
              block.setType(Material.AIR);
              NetherRound.this.blocks.add(block);
            } 
          }
        });
  }
  
  public void reset(Floor floor) {
    for (Block block : this.blocks) {
      Bukkit.getScheduler().runTask((Plugin)iHDeveloperAPI.getPlugin(), new Runnable() {
            public void run() {
              block.setType(Material.WOOL);
            }
          });
    } 
    floor.drawFloor(new BlockChange() {
          public void onBlock(Block block) {
            NetherEnum b = NetherEnum.getById(NetherRound.this.i);
            if (block.getType().equals(b.getType()))
              block.setType(Material.WOOL); 
          }
        });
  }
}
