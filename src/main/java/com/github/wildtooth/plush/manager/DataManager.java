package com.github.wildtooth.plush.manager;

import com.github.wildtooth.plush.Plush;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public class DataManager {

    private final Plush plugin;
    private final YamlConfiguration dataConfig = new YamlConfiguration();
    private File dataFile;

    public DataManager(Plush plugin) {
        this.plugin = plugin;
    }

    /**
     * Loads the data file.
     */
    public void init() {
        this.dataFile = new File(plugin.getDataFolder(), "userdata.yml");
        loadData();
    }

    private void loadData() {
        if (!getDataFile().exists()) {
            getPlugin().saveResource("userdata.yml", false);
        }
        try {
            getDataConfig().load(getDataFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the data file.
     */
    public void save() {
        if (!getDataFile().exists()) {
            getPlugin().saveResource("userdata.yml", false);
        }
        try {
            getDataConfig().save(getDataFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the uuid of a player to the data file, if they are not already in it.
     *
     * @param uuid The uuid of the player to add.
     * @param key The key to add.
     */
    public void addIfAbsent(UUID uuid, String key) {
        if (!hasCredentials(uuid)) {
            getDataConfig().set("users." + uuid + ".credentials", key);
            save();
        }
    }

    /**
     * Adds the players uuid to the data file, if they are not already in it.
     *
     * @param player The player to add the UUID of.
     * @param key The key to add.
     */
    public void addIfAbsent(Player player, String key) {
        if (!hasCredentials(player)) {
            getDataConfig().set("users." + player.getUniqueId() + ".credentials", key);
            save();
        }
    }

    /**
     * Gets the credentials of an uuid.
     *
     * @param uuid the UUID of the player to get the credentials of.
     *
     * @return The credentials of the uuid.
     */
    public String getCredentials(UUID uuid) {
        return getDataConfig().getString("users." + uuid + ".credentials");
    }

    /**
     * Gets the credentials of a player.
     *
     * @param player The player to get the credentials of.
     *
     * @return The credentials of the player.
     */
    public String getCredentials(@NotNull Player player) {
        return getDataConfig().getString("users." + player.getUniqueId() + ".credentials");
    }

    /**
     * Checks if an uuid has credentials.
     *
     * @param uuid The uuid of the player to check.
     *
     * @return Whether the uuid has credentials.
     */
    public boolean hasCredentials(UUID uuid) {
        return getDataConfig().contains("users." + uuid + ".credentials");
    }

    /**
     * Checks if a player has credentials.
     *
     * @param player The player to check.
     *
     * @return Whether the player has credentials.
     */
    public boolean hasCredentials(@NotNull Player player) {
        return getDataConfig().contains("users." + player.getUniqueId() + ".credentials");
    }

    public File getDataFile() {
        return this.dataFile;
    }

    private YamlConfiguration getDataConfig() {
        return this.dataConfig;
    }

    private Plush getPlugin() {
        return this.plugin;
    }
}
