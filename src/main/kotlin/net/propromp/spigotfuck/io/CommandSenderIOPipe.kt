package net.propromp.spigotfuck.io

import net.propromp.brainfuckkt.IOPipe
import org.bukkit.command.CommandSender
import java.util.concurrent.ConcurrentLinkedQueue

open class CommandSenderIOPipe(val sender:CommandSender): IOPipe {
    companion object {
        val instances = mutableMapOf<CommandSender, CommandSenderIOPipe>()
        fun getOrCreate(sender: CommandSender) = instances.getOrPut(sender) { CommandSenderIOPipe(sender) }
    }
    val bytes = ConcurrentLinkedQueue<Byte>()
    var str = ""

    override fun read(): Byte {
        while(true) {
            return bytes.poll() ?: continue
        }
    }

    override fun write(byte: Byte) {
        val char = byte.toInt().toChar()
        if(char=='\n') {
            sender.sendMessage(str)
            str = ""
        } else {
            str+=char
        }
    }
}