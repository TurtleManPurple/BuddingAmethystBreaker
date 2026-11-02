package com.tmpnetwork.buddingamethystbreaker;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class AmethystBreakerCommand implements CommandExecutor {

    private final BuddingAmethystBreaker plugin;

    public AmethystBreakerCommand(BuddingAmethystBreaker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        if (args.length == 0 || !args[0].equalsIgnoreCase("give")) {
            sender.sendMessage(
                    Component.text("Usage: /amethystbreaker give [player]")
                            .color(NamedTextColor.RED)
            );

            return true;
        }

        Player target;

        if (args.length >= 2) {
            target = Bukkit.getPlayerExact(args[1]);

            if (target == null) {
                sender.sendMessage(
                        Component.text("That player is not online.")
                                .color(NamedTextColor.RED)
                );

                return true;
            }
        } else {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(
                        Component.text("Console must specify a player.")
                                .color(NamedTextColor.RED)
                );

                return true;
            }

            target = player;
        }

        ItemStack item = AmethystBreakerItem.create(plugin);

        target.getInventory().addItem(item);

        sender.sendMessage(
                Component.text("Gave the Budding Amethyst Breaker to ")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(target.getName()))
                        .append(Component.text("."))
        );

        return true;
    }
}