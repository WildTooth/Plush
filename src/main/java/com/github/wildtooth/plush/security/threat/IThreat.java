package com.github.wildtooth.plush.security.threat;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * The interface Threat.
 */
public interface IThreat {

    /**
     * Gets the {@link com.github.wildtooth.plush.security.threat.Threat.ThreatType}
     * of the {@link Threat}.
     *
     * @return the description
     */
    Threat.ThreatType getType();

    /**
     * Gets threat description.
     *
     * @return the threat description
     */

    String getDescription();

    /**
     * Gets threat level.
     *
     * @return the threat level
     */
    ThreatLevel getThreatLevel();

    Player getCause();

    /**
     * Execute.
     */
    void execute(@NotNull ThreatLevel severity);

}
