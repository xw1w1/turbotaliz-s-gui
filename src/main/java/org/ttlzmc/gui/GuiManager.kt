package org.ttlzmc.gui

import org.bukkit.entity.Player
import java.util.LinkedList

/**
 * Class for creating, storing and searching Gui
 * @since 1.1
 * @author xw1w1
 */
class GuiManager {

    val eventHandler: GuiEventHandler = GuiEventHandler(this)

    private val registeredGuis: LinkedList<Gui> = LinkedList<Gui>()

    /**
     * Creates a new example of [GuiBuilder].
     * After creation you need to call [GuiManager.registerGui]
     */
    fun builder(rows: Int, block: GuiBuilder.() -> Unit): GuiBuilder {
        return GuiBuilder(rows = rows).apply(block)
    }

    /**
     * Registers this gui by adding it to the list.
     */
    fun registerGui(gui: Gui) {
        this.registeredGuis.add(gui)
    }

    /**
     * Tries to find the Gui this player has open. The method will return either the Gui this player is viewing or null.
     */
    fun findGui(player: Player): Gui? {
        return this.registeredGuis.firstOrNull { it.containsViewer(player) }
    }
}