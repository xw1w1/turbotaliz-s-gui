package org.ttlzmc.gui

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerQuitEvent

/**
 * Handles events for [GuiManager].
 * @see 1.1
 * @author xw1w1
 */
class GuiEventHandler(private val guiManager: GuiManager) : Listener {

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        this.guiManager.findGui(event.player)?.close(event.player)
    }

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val gui = this.guiManager.findGui(event.whoClicked as Player)
        event.isCancelled = true

        if (event.inventory != event.clickedInventory) return
        gui?.handleClick(event.slot, event.whoClicked as Player, event.click)
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as Player
        val gui = this.guiManager.findGui(player)
        if (gui != null && gui.containsViewer(player) ) { gui.close(player) }
    }
}