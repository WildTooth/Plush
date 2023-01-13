package com.github.wildtooth.plush.listener;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.event.item.IllegalItemUseEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * The type Player listener.
 */
public class PlayerListener implements Listener {

    /**
     * Instantiates a new Player listener.
     *
     * @param plugin the plugin
     */
    public PlayerListener(@NotNull Plush plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * On item use.
     *
     * @param event the event
     */
    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
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

    private boolean isIllegalItem(ItemStack itemToCheck, String... illegalMaterials) {
        for (String illegalMaterial : illegalMaterials) {
            if (itemToCheck.getType().name().equalsIgnoreCase(illegalMaterial)) {
                return true;
            }
        }
        return false;
    }
}
