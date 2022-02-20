package net.propromp.spigotfuck.listener

import net.propromp.spigotfuck.io.CommandSenderIOPipe
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.Plugin

class PlayerChatListener(val plugin:Plugin):Listener {
    @EventHandler
    fun onPlayerChat(e:AsyncPlayerChatEvent) {
        val pipe = CommandSenderIOPipe.instances.get(e.player)?:return
        e.message.forEach {
            pipe.bytes.add(it.code.toByte())
        }
    }
}