package com.github.wildtooth.plush.security.threat;

/**
 * The type Threat.
 */
public abstract class Threat implements IThreat {
    private final String description;
    private final ThreatLevel threatLevel;
    private final ThreatType type;


    /**
     * Instantiates a new Threat.
     *
     * @param type        the type
     * @param description the description
     * @param threatLevel the threat level
     */
    protected Threat(ThreatType type, String description, ThreatLevel threatLevel) {
        this.type = type;
        this.description = description;
        this.threatLevel = threatLevel;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }

    public ThreatType getType() {
        return type;
    }

    @Override
    public abstract void execute();

    /**
     * The enum Threat type.
     */
    public enum ThreatType {
        /**
         * Wrong code threat type.
         */
        WRONG_CODE,
        /**
         * Illegal command threat type.
         */
        ILLEGAL_COMMAND,
        /**
         * Illegal item threat type.
         */
        ILLEGAL_ITEM,
        /**
         * Illegal block threat type.
         */
        ILLEGAL_BLOCK,
        /**
         * Illegal entity threat type.
         */
        ILLEGAL_ENTITY
        ;

        ThreatType() {
        }
    }
}
