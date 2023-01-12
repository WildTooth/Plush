package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.security.ThreatHandler;

public interface IThreat {

    String getName();

    ThreatLevel getThreatLevel();

    ThreatHandler getExecutor();

    void setExecutor(ThreatHandler handler);

    void execute(ThreatHandler handler);

}
