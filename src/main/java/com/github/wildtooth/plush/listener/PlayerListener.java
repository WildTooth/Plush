package com.github.wildtooth.plush.listener;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.auth.AuthHandler;
import com.github.wildtooth.plush.event.code.WrongCodeEvent;
import com.github.wildtooth.plush.event.item.IllegalItemUseEvent;
import com.github.wildtooth.plush.manager.DataManager;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * The type Player listener.
 */
public final class PlayerListener implements Listener {

    /**
     * Instantiates a new Player listener.
     *
     * @param plugin the plugin
     */
    public PlayerListener(@NotNull Plush plugin, @NotNull AuthHandler authHandler) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * On item use.
     *
     * @param event the event
     */
    @EventHandler
    public void onItemUse(@NotNull PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        if (isIllegalItem(item, "command")) {
            event.setCancelled(true);

            IllegalItemUseEvent illegalItemUseEvent = new IllegalItemUseEvent(item, player);
            player.getServer().getPluginManager().callEvent(illegalItemUseEvent);
        }

    }

    private boolean isIllegalItem(ItemStack itemToCheck, String @NotNull ... illegalMaterials) {
        for (String illegalMaterial : illegalMaterials) {
            if (itemToCheck.getType().name().equalsIgnoreCase(illegalMaterial)) {
                return true;
            }
        }
        return false;
    }
}
