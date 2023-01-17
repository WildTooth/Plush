package com.github.wildtooth.plush;

import com.github.wildtooth.plush.auth.AuthHandler;
import com.github.wildtooth.plush.auth.AuthListener;
import com.github.wildtooth.plush.listener.PlayerListener;
import com.github.wildtooth.plush.listener.ThreatActivator;
import com.github.wildtooth.plush.log.Log;
import com.github.wildtooth.plush.manager.DataManager;
import com.github.wildtooth.plush.security.handler.ThreatHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plush extends JavaPlugin {

    private static Plush INSTANCE;
    private ThreatHandler threatHandler;
    private AuthHandler authHandler;
    private final DataManager dataManager = new DataManager(this);
    private final Log log = new Log(this);

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveDefaultConfig();
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        // Plugin startup logic
        this.getDataManager().init();

        this.getLogger().info("Initializing Handlers...");
        initializeHandlers();

        this.getLogger().info("Registering Listeners...");
        registerListeners();

        this.getLogger().info("Registering commands...");
        registerCommands();

        this.getLog().init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getDataManager().save();
        getLog().close();
    }

    private void initializeHandlers() {
        threatHandler = new ThreatHandler(getInstance());
        authHandler = new AuthHandler();
    }

    private void registerListeners() {
        new PlayerListener(getInstance(), getAuthHandler());
        new AuthListener(getInstance(), getAuthHandler());
        new ThreatActivator(getInstance());
    }

    @SuppressWarnings({"empty-statement"})
    private void registerCommands() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Plush getInstance() {
        return INSTANCE;
    }

    /**
     * Gets threat handler.
     *
     * @return the threat handler
     */
    public ThreatHandler getThreatHandler() {
        return threatHandler;
    }

    /**
     * Gets auth handler.
     *
     * @return the auth handler
     */
    public AuthHandler getAuthHandler() {
        return authHandler;
    }

    /**
     * Gets data manager.
     *
     * @return the data manager
     */
    public DataManager getDataManager() {
        return dataManager;
    }

    /** Gets log.
     *
     * @return the log
     */
    public Log getLog() {
        return log;
    }
}