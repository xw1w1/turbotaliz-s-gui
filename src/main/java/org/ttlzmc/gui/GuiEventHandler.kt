package org.ttlzmc.gui

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

object GuiEventHandler : Listener {
    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val gui = GuiManager.findGui(event.whoClicked as Player)
        event.isCancelled = true

        if (event.inventory != event.clickedInventory) return
        gui?.handleClick(event.slot, event.whoClicked as Player, event.click)
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as Player
        val gui = GuiManager.findGui(player)
        if (gui != null && gui.containsViewer(player) ) { gui.close(player) }
    }
}