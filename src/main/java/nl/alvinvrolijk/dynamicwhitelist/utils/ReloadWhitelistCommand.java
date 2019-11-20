package nl.alvinvrolijk.dynamicwhitelist.utils;

import nl.alvinvrolijk.dynamicwhitelist.DynamicWhitelist;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;

/**
 *
 * Written by theholyrobber
 */
public class ReloadWhitelistCommand extends BukkitCommand {
    ReloadWhitelistCommand(String name) {
        super(name);
        this.description = "Reload the whitelist config";
        this.usageMessage = "/reloadwhitelist";
        this.setPermission("dynamicwhitelist.reload");
        this.setAliases(new ArrayList<String>());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(ChatColor.RED + "You don't have permission");
            return true;
        }

        if (args.length != 0) {
            return true;
        }

        new Config(DynamicWhitelist.instance, false).reload(); // Reload config
        sender.sendMessage(ChatColor.GOLD + "Whitelist config reloaded"); // Inform player
        return true;
    }
}
