package org.ttlzmc.gui

import org.bukkit.entity.Player

object GuiManager {

    private val registeredGuis = mutableSetOf<Gui>()

    fun builder(rows: Int, block: GuiBuilder.() -> Unit): GuiBuilder {
        return GuiBuilder(rows = rows).apply(block)
    }

    fun registerGui(gui: Gui) {
        registeredGuis.add(gui)
    }

    fun findGui(player: Player): Gui? {
        return registeredGuis.firstOrNull { it.containsViewer(player) }
    }
}