package com.tmpnetwork.buddingamethystbreaker;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class BuddingAmethystBreaker extends JavaPlugin {

    private NamespacedKey itemKey;

    @Override
    public void onEnable() {
        itemKey = new NamespacedKey(this, "budding_amethyst_breaker");

        getServer().getPluginManager().registerEvents(
                new BuddingAmethystListener(this),
                this
        );

        getCommand("amethystbreaker").setExecutor(
                new AmethystBreakerCommand(this)
        );

        registerRecipe();

        getLogger().info("Budding Amethyst Breaker enabled.");
    }

    private void registerRecipe() {
        NamespacedKey recipeKey = new NamespacedKey(
                this,
                "budding_amethyst_breaker"
        );

        ShapedRecipe recipe = new ShapedRecipe(
                recipeKey,
                AmethystBreakerItem.create(this)
        );

        recipe.shape(
                "AAA",
                " D ",
                " D "
        );

        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('D', Material.DIAMOND);

        getServer().addRecipe(recipe);
    }

    public NamespacedKey getItemKey() {
        return itemKey;
    }
}