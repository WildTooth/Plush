package com.github.wildtooth.plush.interfaces;

import com.github.wildtooth.plush.enums.Hook;
import org.bukkit.plugin.java.JavaPlugin;

public interface IHook {

    /**
     * Get the name of the hook
     * @return Hook name
     */
    String getName();

    /**
     * Get the enum of the hook
     * @return Hook enum
     */
    Hook getEnum();

    /**
     * Check if the hook is enabled
     * @return Hook enabled
     */
    boolean isEnabled();

    /**
     * Initialise the hook
     * @param plugin JavaPlugin
     * @return if the hook was initialised
     */
    boolean init(JavaPlugin plugin);
}
