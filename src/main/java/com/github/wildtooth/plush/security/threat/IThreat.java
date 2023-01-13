package com.github.wildtooth.plush.security.threat;

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
     * Execute.
     */
    void execute();

}
