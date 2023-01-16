package com.github.wildtooth.plush;

import com.github.wildtooth.plush.auth.AuthHandler;
import com.github.wildtooth.plush.listener.PlayerListener;
import com.github.wildtooth.plush.listener.ThreatActivator;
import com.github.wildtooth.plush.manager.DataManager;
import com.github.wildtooth.plush.security.handler.ThreatHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plush extends JavaPlugin {

    private static Plush INSTANCE;
    private ThreatHandler threatHandler;
    private AuthHandler authHandler;
    private final DataManager dataManager = new DataManager(this);

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.saveDefaultConfig();
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        // Plugin startup logic
        this.getDataManager().init();
        initializeHandlers();
        registerListeners();
    }

    private void initializeHandlers() {
        threatHandler = new ThreatHandler();
        authHandler = new AuthHandler();
    }

    private void registerListeners() {
        new PlayerListener(getInstance(), getAuthHandler());
        new ThreatActivator(getInstance());
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
}