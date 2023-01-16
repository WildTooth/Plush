package com.github.wildtooth.plush.listener;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.auth.AuthHandler;
import com.github.wildtooth.plush.event.code.WrongCodeEvent;
import com.github.wildtooth.plush.event.item.IllegalItemUseEvent;
import com.github.wildtooth.plush.manager.DataManager;
import com.github.wildtooth.plush.security.threat.WrongCodeThreat;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.bukkit.Bukkit;
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

    private final DataManager dataManager;
    private final AuthHandler authHandler;

    /**
     * Instantiates a new Player listener.
     *
     * @param plugin the plugin
     */
    public PlayerListener(@NotNull Plush plugin, @NotNull AuthHandler authHandler) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.authHandler = authHandler;
        this.dataManager = plugin.getDataManager();
    }

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!this.dataManager.hasCredentials(player)) {
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            GoogleAuthenticatorKey key = gAuth.createCredentials();

            player.sendMessage("§7You must enter this code in the Google Authenticator App before leaving the server.");
            player.sendMessage("§7Your §bGoogle Auth Code §7is §a" + key.getKey());

            this.dataManager.addIfAbsent(player, key.getKey());
        } else {
            authHandler.handle(player);
            player.sendMessage("§cPlease open the Google Authenticator App and provide the six digit code.");
        }
    }

    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();
        authHandler.handle(uuid);
    }

    @EventHandler
    public void onChat(@NotNull AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();

        if (authHandler.isAuthLocked(player)) {
            try {
                Integer code = Integer.parseInt(message);
                if (playerInputCode(player, code)) {
                    authHandler.handle(player);
                    player.sendMessage("§a*Access Granted* §bWelcome to the server!");
                } else {
                    player.sendMessage("§cIncorrect or expired code ** A code will only contain numbers **");
                    WrongCodeEvent wrongCodeEvent = new WrongCodeEvent(code, player);
                    player.getServer().getPluginManager().callEvent(wrongCodeEvent);
                }
            } catch (Exception e) {
                player.sendMessage("§cIncorrect or expired code ** A code will only contain numbers **");
            }
            event.setCancelled(true);
        }
    }

    private boolean playerInputCode(Player player, int code) {
        final String secretKey = this.dataManager.getCredentials(player);

        GoogleAuthenticator gAuth = new GoogleAuthenticator();

        return gAuth.authorize(secretKey, code);
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
