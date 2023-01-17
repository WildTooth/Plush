package com.github.wildtooth.plush.manager;

public class LangHandler {

    public enum LangMessages {
        // Auth
        AUTH_NEW_SECURE_USER("auth.new-secure-user"),
        AUTH_LOCKED("auth.locked"),
        AUTH_UNLOCKED("auth.unlocked"),
        AUTH_SUCCESS("auth.success"),
        AUTH_FAILURE("auth.failure"),
        AUTH_INVALID("auth.invalid"),

        // Threat
        THREAT_LEVEL_LOW("threat.threat-level-low"),
        THREAT_LEVEL_MEDIUM("threat.threat-level-medium"),
        THREAT_LEVEL_HIGH("threat.threat-level-high"),

        ILLEGAL_ENTITY_DAMAGE_MESSAGE("threat.illegal-entity-damage.message"),
        ILLEGAL_ENTITY_DAMAGE_DESCRIPTION("threat.illegal-entity-damage.description"),

        ILLEGAL_ENTITY_INTERACT_MESSAGE("threat.illegal-entity-interact.message"),
        ILLEGAL_ENTITY_INTERACT_DESCRIPTION("threat.illegal-entity-interact.description"),

        ILLEGAL_ITEM_DROP_MESSAGE("threat.illegal-item-drop.message"),
        ILLEGAL_ITEM_DROP_DESCRIPTION("threat.illegal-item-drop.description"),

        ILLEGAL_ITEM_PICKUP_MESSAGE("threat.illegal-item-pickup.message"),
        ILLEGAL_ITEM_PICKUP_DESCRIPTION("threat.illegal-item-pickup.description"),

        ILLEGAL_ITEM_USE_MESSAGE("threat.illegal-item-use.message"),
        ILLEGAL_ITEM_USE_DESCRIPTION("threat.illegal-item-use.description"),



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
