package net.propromp.spigotfuck.listener

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class ListenerRegistry(val plugin:Plugin) {
    fun register(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener,plugin)
    }
    fun load() {
        register(PlayerChatListener(plugin))
    }
}