package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.security.handler.ThreatHandler;

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

    /**
     * Gets executor.
     *
     * @return the executor
     */
    ThreatHandler getExecutor();

    /**
     * Sets executor.
     *
     * @param handler the handler
     */
    void setExecutor(ThreatHandler handler);

    /**
     * Execute.
     */
    void execute();

}
