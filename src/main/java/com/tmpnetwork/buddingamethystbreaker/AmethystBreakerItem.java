package com.tmpnetwork.buddingamethystbreaker;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public final class AmethystBreakerItem {

    private AmethystBreakerItem() {
    }

    public static ItemStack create(BuddingAmethystBreaker plugin) {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);

        ItemMeta meta = item.getItemMeta();

        meta.displayName(
                Component.text("Budding Amethyst Breaker")
                        .color(NamedTextColor.LIGHT_PURPLE)
                        .decoration(TextDecoration.ITALIC, false)
        );

        meta.lore(List.of(
                Component.text("Can break budding amethyst.")
                        .color(NamedTextColor.GRAY)
                        .decoration(TextDecoration.ITALIC, false)
        ));

        meta.getPersistentDataContainer().set(
                plugin.getItemKey(),
                PersistentDataType.BYTE,
                (byte) 1
        );

        item.setItemMeta(meta);

        return item;
    }

    public static boolean isBreaker(
            BuddingAmethystBreaker plugin,
            ItemStack item
    ) {
        if (item == null || item.getType() != Material.DIAMOND_PICKAXE) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return false;
        }

        return meta.getPersistentDataContainer().has(
                plugin.getItemKey(),
                PersistentDataType.BYTE
        );
    }
}