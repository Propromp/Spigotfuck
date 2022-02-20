package net.propromp.spigotfuck

import net.propromp.neocommander.api.CommandManager
import net.propromp.neocommander.api.builder.CommandBuilder
import net.propromp.spigotfuck.command.CommandRegistry
import net.propromp.spigotfuck.listener.ListenerRegistry
import org.bukkit.plugin.java.JavaPlugin

class Spigotfuck:JavaPlugin() {
    lateinit var listenerRegistry:ListenerRegistry
    lateinit var commandRegistry: CommandRegistry
    override fun onEnable() {
        //listener
        listenerRegistry = ListenerRegistry(this)
        listenerRegistry.load()
        //command
        commandRegistry = CommandRegistry(this)
        commandRegistry.load()
    }
}