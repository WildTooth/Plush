package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.security.handler.ThreatHandler;
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
     * Gets the description of the {@link Threat}.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets the {@link ThreatHandler} that will execute the {@link Threat}.
     *
     * @return the executor
     */
    ThreatHandler getExecutor();

    /**
     * Sets the {@link ThreatHandler} that will execute the {@link Threat}.
     *
     * @param executor the executor
     */
    void setExecutor(ThreatHandler executor);

    /**
     * Gets the {@link ThreatLevel} of the {@link Threat}.
     *
     * @return the threat level
     */
    ThreatLevel getThreatLevel();

    /**
     * Gets the {@link Player} that caused the {@link Threat}.
     *
     * @return the cause
     */
    Player getCause();

    /**
     * Execute the {@link Threat} with the given {@link ThreatLevel}.
     */
    void execute(@NotNull ThreatLevel severity);

}
