package me.iHDeveloper.api.player;

import me.iHDeveloper.api.Debug;
import me.iHDeveloper.api.iHDeveloperAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UUIDDatabase {
    private Map<String, String> uuids;

    @SuppressWarnings("unchecked")
    public UUIDDatabase() {
        try {
            YamlConfiguration c = getUUIDYMLData();
            Object obj = c.get("uuids");
            if (obj instanceof Map) {
                this.uuids = (Map<String, String>) obj;
            }
        } catch (NullPointerException ex) {
            this.uuids = new HashMap<>();
            Debug.error("Can't load uuids database");
        }
        if (this.uuids == null)
            this.uuids = new HashMap<>();
    }

    public UUID get(Player p) {
        String rawUUID = this.uuids.get(p.getName());
        UUID uuid;
        if (rawUUID == null) {
            update(p);
            return p.getUniqueId();
        }

        uuid = UUID.fromString(rawUUID);
        Debug.log("UUIDDatabase - Get [name='" + p.getName() + "',uuid'" + uuid + "']");
        return uuid;
    }

    public UUID get(String name) {
        String rawUUID = this.uuids.get(name);
        if (rawUUID == null) {
            return Bukkit.getPlayer(name).getUniqueId();
        }
        return UUID.fromString(rawUUID);
    }

    public void update(HDPlayer player) {
        update(player.getPlayer());
    }

    public void update(Player player) {
        UUID old;
        UUID update = player.getUniqueId();

        String rawOldUUID = this.uuids.get(player.getName());
        if (rawOldUUID == null) {
            this.uuids.put(player.getName(), update.toString());
            return;
        }

        old = UUID.fromString(rawOldUUID);

        if (!old.equals(update)) {
            this.uuids.put(player.getName(), update.toString());
            return;
        }
        Debug.log("UUIDDatabase - Update [old='" + old + "',update='" + update + "']");
        saveDataBase();
    }

    private void saveDataBase() {
        try {
            YamlConfiguration c = getUUIDYMLData();
            c.set("uuids", this.uuids);
            c.save(getUUIDYMLDataFile());
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.error("Cannot save the uuids yml data");
        }
    }

    private File getUUIDYMLDataFile() {
        return new File(iHDeveloperAPI.getDataFolder() + "/uuids.ymldata");
    }

    private YamlConfiguration getUUIDYMLData() {
        YamlConfiguration c = new YamlConfiguration();
        try {
            File dataFile = getUUIDYMLDataFile();
            if (!dataFile.createNewFile())
                c.load(dataFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
