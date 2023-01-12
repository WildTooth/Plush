package com.github.wildtooth.plush.security;

import com.github.wildtooth.plush.security.threat.Threat;

public class ThreatHandler {
    public void handleThreat(Threat threat) {
        threat.execute(this);
    }
}
