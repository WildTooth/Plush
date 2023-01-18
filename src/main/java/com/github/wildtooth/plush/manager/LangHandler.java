package com.github.wildtooth.plush.manager;

import com.github.wildtooth.plush.Plush;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class LangHandler {

    private Plush plugin = null;
    private File langFile = null;
    private FileConfiguration lang = null;

    public LangHandler(Plush plugin) {
        this.plugin = plugin;
    }


    public enum LangMessages {
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

        ILLEGAL_ITEM_USE_MESSAGE("threat.illegal-item-use.message"),
        ILLEGAL_ITEM_USE_DESCRIPTION("threat.illegal-item-use.description"),

        // General
        GENERAL_NO_PERMISSION("general.no-permission"),

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
