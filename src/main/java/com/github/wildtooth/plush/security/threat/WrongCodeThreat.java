package com.github.wildtooth.plush.security.threat;

import org.bukkit.entity.Player;

/**
 * The type Wrong code threat.
 */
public final class WrongCodeThreat extends Threat {

    private final Integer code;

    public WrongCodeThreat(String description,
                           ThreatLevel threatLevel,
                           Player theCause,
                           Integer theCode) {
        super(ThreatType.WRONG_CODE, description, threatLevel, theCause);
        this.code = theCode;
    }

    public Integer getCode() {
        return code;
    }
}
