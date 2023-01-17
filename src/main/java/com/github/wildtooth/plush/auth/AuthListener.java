package com.github.wildtooth.plush.auth;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.event.code.WrongCodeEvent;
import com.github.wildtooth.plush.manager.DataManager;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AuthListener implements Listener {
    private final DataManager dataManager;
    private final AuthHandler authHandler;

    /**
     * Instantiates a new Authentication related listener.
     *
     * @param plugin the plugin
     */
    public AuthListener(@NotNull Plush plugin, @NotNull AuthHandler authHandler) {
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

            player.sendMessage("§7Enter code in app.");
            player.sendMessage("§7Your §bGoogle Auth Code §7is §a" + key.getKey());

            this.dataManager.addIfAbsent(player, key.getKey());
        } else {
            authHandler.handle(player);
            player.sendMessage("§cProvide six digit code.");
        }
    }

    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        if (authHandler.isAuthLocked(event.getPlayer())) {
            final UUID uuid = event.getPlayer().getUniqueId();
            authHandler.handle(uuid);
        }
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
                    player.sendMessage("You now have access to the server.");
                } else {
                    player.sendMessage("§cIncorrect code.");
                    WrongCodeEvent wrongCodeEvent = new WrongCodeEvent(code, player);
                    player.getServer().getPluginManager().callEvent(wrongCodeEvent);
                }
            } catch (NumberFormatException e) {
                player.sendMessage("§cInvalid code.");
            }
            event.setCancelled(true);
        }
    }

    private boolean playerInputCode(Player player, int code) {
        final String secretKey = this.dataManager.getCredentials(player);

        GoogleAuthenticator gAuth = new GoogleAuthenticator();

        return gAuth.authorize(secretKey, code);
    }
}
