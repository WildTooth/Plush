package com.github.wildtooth.plush.security.handler;

import com.github.wildtooth.plush.security.threat.Threat;
import org.jetbrains.annotations.NotNull;

/**
 * The type Threat handler.
 */
public class ThreatHandler {

    /**
     * Instantiates a new Threat handler.
     */
    public ThreatHandler() {
    }

    /**
     * Handle.
     *
     * @param threat the threat
     */
    public void handle(@NotNull Threat threat) {
        threat.execute(threat.getThreatLevel());
    }
}
