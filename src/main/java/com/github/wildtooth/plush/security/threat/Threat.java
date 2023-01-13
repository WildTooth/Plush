package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.security.handler.ThreatHandler;

/**
 * The type Threat.
 */
public abstract class Threat implements IThreat {
    private final String description;
    private final ThreatLevel threatLevel;
    private ThreatHandler executor;
    private ThreatType type;


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

    @Override
    public ThreatHandler getExecutor() {
        return executor;
    }

    public ThreatType getType() {
        return type;
    }

    @Override
    public void setExecutor(ThreatHandler handler) {
        this.executor = handler;
    }

    @Override
    public abstract void execute();

    public enum ThreatType {
        WRONG_CODE,
        ILLEGAL_COMMAND,
        ILLEGAL_ITEM,
        ILLEGAL_BLOCK,
        ILLEGAL_ENTITY,

        ThreatType() {
        }
    }
}
