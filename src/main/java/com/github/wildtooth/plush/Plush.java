package com.github.wildtooth.plush;

import com.github.wildtooth.plush.listener.PlayerListener;
import com.github.wildtooth.plush.listener.ThreatActivator;
import com.github.wildtooth.plush.security.handler.ThreatHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plush extends JavaPlugin {

    private static Plush INSTANCE;
    private ThreatHandler threatHandler;

    @Override
    public void onEnable() {
        INSTANCE = this;
        initializeHandlers();
        registerListeners();
    }

    private void initializeHandlers() {
        threatHandler = new ThreatHandler();
    }

    private void registerListeners() {
        new PlayerListener(getInstance());
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
}