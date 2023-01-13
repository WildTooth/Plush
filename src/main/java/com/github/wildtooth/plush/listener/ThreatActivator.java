package com.github.wildtooth.plush.listener;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.event.item.IllegalItemUseEvent;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class ThreatActivator implements Listener {

    public ThreatActivator(@NotNull Plush plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void onIllegalItemUse(IllegalItemUseEvent event) {

    }
}
