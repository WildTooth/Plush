package com.github.wildtooth.plush.security.handler;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.security.threat.Threat;
import org.jetbrains.annotations.NotNull;

/**
 * The type Threat handler.
 */
public class ThreatHandler {

    private final Plush plugin;

    /**
     * Instantiates a new Threat handler.
     */
    public ThreatHandler(@NotNull Plush plugin) {
        this.plugin = plugin;
    }


    /**
     * Handle.
     *
     * @param threat the threat
     */
    public void handle(@NotNull Threat threat) {
        threat.execute(threat.getThreatLevel());
    }

    /**
     * Gets plugin.
     *
     * @return the plugin
     */
    public Plush getPlugin() {
        return plugin;
    }
}
