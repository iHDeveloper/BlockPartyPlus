package sa.heroz.blockpartypls.until;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import sa.heroz.blockpartypls.until.floor.BlockChange;
import sa.heroz.blockpartypls.until.floor.FloorVaildChecker;
import sa.heroz.blockpartypls.until.floor.FloorVaildCheckerException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Floor {
    public void drawFloor(final BlockChange change) {
        Location pos1 = Settings.pos1;
        Location pos2 = Settings.pos2;
        int x1 = pos1.getBlockX();
        int x2 = pos2.getBlockX();
        int y1 = pos1.getBlockY();
        int y2 = pos2.getBlockY();
        int z1 = pos1.getBlockZ();
        int z2 = pos2.getBlockZ();
        Debug.log("Recording locations: X [ 1:%s 2:%s ] Y [ 1:%s 2:%s ] Z [ 1:%s 2:%s ]", x1, x2, y1, y2, z1, z2);
        try {
            FloorVaildChecker.check(x1, x2, "x");
        } catch (FloorVaildCheckerException exception) {
            exception.printStackTrace();
        }
        Map<Integer, ArrayList<Block>> blocks = new HashMap<>();
        for (int x = x1; x < x2 + 1; x++) {
            ArrayList<Block> blocksList = new ArrayList<>();
            for (int y = y1; y < y2 + 1; y++) {
                for (int z = z1; z < z2 + 1; z++) {
                    Block block = pos1.getWorld().getBlockAt(x, y, z);
                    blocksList.add(block);
                }
            }
            blocks.put(x, blocksList);
        }
        for (int i : blocks.keySet()) {
            final ArrayList<Block> list = blocks.get(i);
            Bukkit.getScheduler().runTask(iHDeveloperAPI.getPlugin(), () -> {
                for (Block block : list)
                    change.onBlock(block);
            });
        }
    }
}
