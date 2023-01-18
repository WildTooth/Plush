package com.github.wildtooth.plush.hook;

import com.github.wildtooth.plush.exceptions.HookNotEnabledException;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class VaultHook extends Hook {
    private static Permission PERMISSION = null;

    private static final String PERMISSION_EXCEPTION = "Tried using Vault's Permission Provider, but none was provided during initialising.";

    public VaultHook() {
        super("Vault", com.github.wildtooth.plush.enums.Hook.VAULT);
    }

    /**
     *
     * Initialising the {@link VaultHook}.
     *
     * @param paramPlugin The core plugin.
     * @return if the hook is established currently.
     */
    @Override
    public boolean init(JavaPlugin paramPlugin) {
        if(!super.isEnabled()) return false;

        RegisteredServiceProvider<Permission> rspPermission = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);

        if(rspPermission != null) PERMISSION = rspPermission.getProvider();
        else Bukkit.getLogger().severe("[VaultHook] No Permission Provider was found.");

        return (PERMISSION != null);
    }

    /**
     *
     * @param player the {@link Player} to get the group of
     * @return the group of the {@link Player}
     */
    public static String getPrimaryGroup(Player player){
        if(PERMISSION == null)
            throw new HookNotEnabledException(PERMISSION_EXCEPTION);
        return PERMISSION.getPrimaryGroup(player);
    }

    /**
     *
     * @param player the {@link Player} to get the groups of
     * @return the groups of the {@link Player}
     */
    public static String[] getPlayerGroups(Player player){
        if(PERMISSION == null)
            throw new HookNotEnabledException(PERMISSION_EXCEPTION);
        return PERMISSION.getPlayerGroups(player);
    }

}

