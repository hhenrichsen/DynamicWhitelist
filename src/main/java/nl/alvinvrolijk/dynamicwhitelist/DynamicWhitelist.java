package nl.alvinvrolijk.dynamicwhitelist;

import nl.alvinvrolijk.dynamicwhitelist.utils.Config;
import nl.alvinvrolijk.dynamicwhitelist.utils.WhitelistChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class DynamicWhitelist extends JavaPlugin {

    public static DynamicWhitelist instance; // Instance

    public Logger logger = Logger.getLogger(getDescription().getName()); // Set console logger

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this; // Set instance

        new Config(this, true); // Get config file

        getServer().getPluginManager().registerEvents(new WhitelistChecker(this), this); // Register listener

        logger.info("Plugin enabled"); // Inform console that the plugin is enabled
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null; // Set instance to null

        logger.info("Plugin disabled"); // Inform console that the plugin is disabled
    }
}
