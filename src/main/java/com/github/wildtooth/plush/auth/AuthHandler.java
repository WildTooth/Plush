package com.github.wildtooth.plush.auth;

import org.bukkit.Bukkit;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public final class AuthHandler {
    private final ArrayList<UUID> authLocked;

    public AuthHandler() {
        authLocked = new ArrayList<>();
    }

    /**
     * Locks and unlocks a player's account.
     *
     * @param uuid The uuid of the player to lock/unlock.
     */
    public void handle(UUID uuid) {
        toggleAuthLocked(uuid);
    }

    /**
     * Toggles the authLocked status of a {@link Player}.
     * @param player The player to handle the {@link UUID} of.
     */
    public void handle(@NotNull Player player){
        handle(player.getUniqueId());
    }

    /** @deprecated Use {@link #handle(UUID)} or {@link #handle(Player)} instead */
    @Deprecated
    @Warning(reason = "This method is deprecated and will be removed in a future version")
    public void handle(String name){
        handle(Bukkit.getPlayer(name).getUniqueId());
    }

    /**
     * Checks if an {@link UUID} of a {@link Player} is authLocked.
     *
     * @param uuid The uuid of the player to check.
     *
     * @return True if the player is authLocked, false if not.
     */
    public boolean isAuthLocked(UUID uuid) {
        return authLocked.contains(uuid);
    }

    /**
     * Checks if an {@link UUID} of a {@link Player} is authLocked.
     *
     * @param player The player to check the uuid of.
     *
     * @return True if the player is authLocked, false if not.
     */
    public boolean isAuthLocked(@NotNull Player player) {
        return authLocked.contains(player.getUniqueId());
    }

    /**
     * Clears the list of {@link UUID}'s of {@link Player}'s.
     */
    public void clearAuthLocked() {
        authLocked.clear();
    }

    private void toggleAuthLocked(UUID uuid) {
        if (isAuthLocked(uuid)) {
            setAuthLocked(uuid, false);
        } else {
            setAuthLocked(uuid, true);
        }
    }

    private void setAuthLocked(UUID uuid, boolean locked) {
        if (locked) {
            authLocked.add(uuid);
        } else {
            authLocked.remove(uuid);
        }
    }
}
