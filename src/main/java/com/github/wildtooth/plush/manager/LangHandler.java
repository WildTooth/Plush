package com.github.wildtooth.plush.manager;

public class LangHandler {

    public enum LangMessages {
        // Auth
        AUTH_NEW_SECURE_USER("auth.new-secure-user"),
        AUTH_LOCKED("auth.locked"),
        AUTH_UNLOCKED("auth.unlocked"),
        AUTH_SUCCESS("auth.success"),
        AUTH_FAILURE("auth.failure"),

        // Threat
        THREAT_LEVEL_LOW("threat.threat-level-low"),
        THREAT_LEVEL_MEDIUM("threat.threat-level-medium"),
        THREAT_LEVEL_HIGH("threat.threat-level-high"),




        ERROR_NO_PERMISSION("error.no-permission"),

        ;

        private final String path;

        private LangMessages(String s) {
            this.path = s;
        }

        public String getPath() {
            return path;
        }
    }
}
