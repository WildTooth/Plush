package com.github.wildtooth.plush;

import com.github.wildtooth.plush.security.ThreatHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plush extends JavaPlugin {

    private static Plush INSTANCE;
    private ThreatHandler threatHandler;

    @Override
    public void onEnable() {
        INSTANCE = this;
        threatHandler = new ThreatHandler();
    }

    public static Plush getInstance() {
        return INSTANCE;
    }

    public ThreatHandler getThreatHandler() {
        return threatHandler;
    }
}