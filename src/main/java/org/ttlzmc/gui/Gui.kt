package org.ttlzmc.gui

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class Gui(
    rows: Int,
    name: Component,
    backgroundMaterial: (() -> Material)?,
    private val tiles: Int2ObjectOpenHashMap<Tile>
) {
    private val inventory: Inventory = Bukkit.createInventory(null, rows * 9, name)
    private val viewers = mutableSetOf<Player>()

    init {
        backgroundMaterial?.let { bg ->
            val bgItem = ItemStack(bg())
            for (i in 0 until inventory.size) {
                inventory.setItem(i, bgItem)
            }
        }

        tiles.values.forEach { tile ->
            inventory.setItem(Tile.calculatePosition(tile), tile.itemStack())
        }
    }

    fun open(player: Player) {
        viewers.add(player)
        player.openInventory(inventory)
    }

    fun close(player: Player) {
        player.closeInventory(InventoryCloseEvent.Reason.PLUGIN)
    }

    fun containsViewer(player: Player): Boolean {
        return this.viewers.contains(player)
    }

    fun handleClick(slot: Int, player: Player, clickType: ClickType) {
        val tile = this.tiles[slot]
        tile?.clickAction?.onClick(player, tile, clickType)
    }
}