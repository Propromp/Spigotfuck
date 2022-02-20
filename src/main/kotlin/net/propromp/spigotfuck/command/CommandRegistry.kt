package net.propromp.spigotfuck.command

import net.propromp.brainfuckkt.BrainfuckInterpreter
import net.propromp.neocommander.api.CommandManager
import net.propromp.neocommander.api.argument.StringArgument
import net.propromp.neocommander.api.builder.CommandBuilder
import net.propromp.spigotfuck.io.CommandSenderIOPipe
import org.bukkit.plugin.Plugin

class CommandRegistry(plugin:Plugin) {
    val commandManager = CommandManager(plugin)
    fun load() {
        commandManager.register(
            CommandBuilder("brainfuck")
                .arguments(StringArgument("brainfuck"))
                .executes {
                    val brainfuck = it.getArgument("brainfuck",String::class.java)
                    it.source.sendMessage("brainfuckを実行します")
                    val pipe = CommandSenderIOPipe.getOrCreate(it.source.sender)
                    val interpreter = BrainfuckInterpreter(brainfuck, pipe)
                    val mill = System.currentTimeMillis()
                    interpreter.execute()
                    it.source.sendMessage(pipe.str)
                    pipe.str = ""
                    it.source.sendMessage("${System.currentTimeMillis()-mill}msで実行しました。")
                }.build()
        )
    }
}