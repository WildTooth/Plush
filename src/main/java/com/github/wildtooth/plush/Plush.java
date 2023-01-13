package com.github.wildtooth.plush;

import com.github.wildtooth.plush.security.handler.ThreatHandler;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The type Plush.
 */
public final class Plush extends JavaPlugin {

    private static Plush INSTANCE;
    private ThreatHandler threatHandler;

    @Override
    public void onEnable() {
        INSTANCE = this;
        threatHandler = new ThreatHandler();
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