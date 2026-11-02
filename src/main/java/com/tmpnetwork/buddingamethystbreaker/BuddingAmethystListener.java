package com.tmpnetwork.buddingamethystbreaker;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public final class BuddingAmethystListener implements Listener {

    private final BuddingAmethystBreaker plugin;

    public BuddingAmethystListener(BuddingAmethystBreaker plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!AmethystBreakerItem.isBreaker(plugin, item)) {
            return;
        }

        Material blockType = block.getType();

        if (blockType != Material.BUDDING_AMETHYST
                && blockType != Material.AMETHYST_CLUSTER) {
            event.setCancelled(true);
            return;
        }

        event.setCancelled(true);

        block.setType(Material.AIR, false);

        if (player.getGameMode() != GameMode.CREATIVE) {
            if (blockType == Material.BUDDING_AMETHYST) {
                player.getServer().dispatchCommand(
                        player.getServer().getConsoleSender(),
                        "give " + player.getName() + " minecraft:budding_amethyst"
                );
            } else {
                block.getWorld().dropItemNaturally(
                        block.getLocation(),
                        new ItemStack(Material.AMETHYST_CLUSTER)
                );
            }

            item.damage(1, player);
        }

        block.getWorld().playSound(
                block.getLocation(),
                Sound.BLOCK_AMETHYST_BLOCK_BREAK,
                1.0f,
                1.0f
        );
    }
}