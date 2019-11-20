package nl.alvinvrolijk.dynamicwhitelist.utils;

import nl.alvinvrolijk.dynamicwhitelist.DynamicWhitelist;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class WhitelistChecker implements Listener {

    private DynamicWhitelist instance;

    private Config config;

    public WhitelistChecker(DynamicWhitelist instance) {
        this.instance = instance;
        this.config = new Config(instance, false);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");

        if (instance.getServer().hasWhitelist()) {
            if (e.getPlayer().hasPermission("dynamicwhitelist.whitelist") || (config.get().getBoolean("automatic-whitelist-for-ops") && e.getPlayer().isOp()) || (config.get().getBoolean("use-vanilla-whitelist") && e.getPlayer().isWhitelisted())) {
                sendJoinLeaveMessage("join", e.getPlayer().getDisplayName());
            } else {
                e.getPlayer().kickPlayer("You are not whitelisted on this server!");e.getPlayer().kickPlayer("You are not whitelisted on this server!");
            }
        } else {
            sendJoinLeaveMessage("join", e.getPlayer().getDisplayName());
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage("");

        if (instance.getServer().hasWhitelist()) {
            if (e.getPlayer().hasPermission("dynamicwhitelist.whitelist") || (config.get().getBoolean("automatic-whitelist-for-ops") && e.getPlayer().isOp()) || (config.get().getBoolean("use-vanilla-whitelist") && e.getPlayer().isWhitelisted())) {
                sendJoinLeaveMessage("leave", e.getPlayer().getDisplayName());
            }
        } else {
            sendJoinLeaveMessage("leave", e.getPlayer().getDisplayName());
        }
    }

    public void sendJoinLeaveMessage(String type, String playerName) {
        String message = null;

        switch (type) {
            case "join":
                if (config.get().getBoolean("announcements.join.toggle")) {
                    message = config.get().getString("announcements.join.message");
                }
                break;
            case "leave":
                if (config.get().getBoolean("announcements.leave.toggle")) {
                    message = config.get().getString("announcements.leave.message");
                }
                break;
        }

        if (message != null) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{player_name}", playerName)));
        }
    }
}