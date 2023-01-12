package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.security.ThreatHandler;

public abstract class Threat implements IThreat {

    private final String name;
    private final ThreatLevel threatLevel;
    private ThreatHandler executor;

    public Threat(String name, ThreatLevel threatLevel) {
        this.name = name;
        this.threatLevel = threatLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }

    @Override
    public ThreatHandler getExecutor() {
        return executor;
    }
    @Override
    public void setExecutor(ThreatHandler handler) {
        this.executor = handler;
    }

    @Override
    public abstract void execute(ThreatHandler handler);
}
