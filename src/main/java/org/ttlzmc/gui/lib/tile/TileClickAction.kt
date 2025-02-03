package org.ttlzmc.gui.lib.tile

import net.kyori.adventure.sound.Sound
import org.bukkit.Bukkit
import org.bukkit.entity.Player

@FunctionalInterface
fun interface TileClickAction {

    fun onClick(player: Player, button: Tile)

    /**
     * Example GUI button action.
     * @return **ButtonClickAction**, which will close the currently open inventory on click
     */
    fun close(): TileClickAction = TileClickAction { sender: Player, _: Tile -> sender.closeInventory() }

    /**
     * Example GUI button action.
     *
     * @return **PlaySound** action, which will play specified sound on click
     */
    fun sound(sound: Sound) = PlaySound(sound)

    /**
     * Example GUI button action.
     *
     * @return **ExecuteCommand** action, which will run specified command on click
     */
    fun command(command: String) = ExecuteCommand(command)

    class ExecuteCommand(private val command: String) : TileClickAction {
        override fun onClick(player: Player, button: Tile) {
            Bukkit.dispatchCommand(player, command)
        }

        fun commandString() = command
    }

    class PlaySound(private val sound: Sound) : TileClickAction {
        override fun onClick(player: Player, button: Tile) {
            player.playSound(sound)
        }

        fun getSound() = sound
    }

}