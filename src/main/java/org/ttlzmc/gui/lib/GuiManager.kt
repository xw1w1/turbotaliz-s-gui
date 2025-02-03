package org.ttlzmc.gui.lib

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import java.util.LinkedList

/**
 * A class dealing with storing, registering and deleting [GuiContainer] instances,
 *
 * listening for container events, directing tile-related actions.
 * @see GuiContainer
 * @see GuiContainerPage
 */
object GuiManager : Listener {

    private val instances = LinkedList<GuiContainer>()

    fun register(gui: GuiContainer) = instances.add(gui)
    fun unregister(gui: GuiContainer) = instances.remove(gui)

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val page = getExactPage(viewer = event.whoClicked as Player)
        event.isCancelled = true

        if (event.inventory != event.clickedInventory) return
        page?.click(event.slot, event.whoClicked as Player)
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val page = getExactPage(viewer = event.player as Player)
        page?.close(event.player as Player)
    }

    /**
     * Searches among all instanced containers and their pages for the one the **viewer** is looking at
     */
    private fun getExactPage(viewer: Player): GuiContainerPage? {
        return this.instances
            .asSequence()
            .mapNotNull { container ->
                container.pages().firstOrNull { it.containsViewer(viewer) }
            }
            .firstOrNull()
    }
}