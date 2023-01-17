package com.github.wildtooth.plush.listener;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.event.code.WrongCodeEvent;
import com.github.wildtooth.plush.event.item.IllegalItemUseEvent;
import com.github.wildtooth.plush.security.threat.IllegalUseThreat;
import com.github.wildtooth.plush.security.threat.ThreatLevel;
import com.github.wildtooth.plush.security.threat.WrongCodeThreat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * The type Threat activator.
 */
public final class ThreatActivator implements Listener {

    private final Plush plugin;

    /**
     * Instantiates a new Threat activator.
     *
     * @param plugin the plugin
     */
    public ThreatActivator(@NotNull Plush plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onWrongCodeInput(@NotNull WrongCodeEvent event) {
        WrongCodeThreat threat = new WrongCodeThreat(
                "Wrong code input detected",
                ThreatLevel.LOW,
                event.getPlayer(),
                event.getCode());

        plugin.getThreatHandler().handle(threat);
    }

    /**
     * On illegal item use.
     *
     * @param event the event
     */
    @EventHandler
    public void onIllegalItemUse(@NotNull IllegalItemUseEvent event) {
        IllegalUseThreat threat = new IllegalUseThreat(
                "Illegal item use detected",
                ThreatLevel.LOW,
                event.getItem(),
                event.getPlayer());

        plugin.getThreatHandler().handle(threat);
    }
}
