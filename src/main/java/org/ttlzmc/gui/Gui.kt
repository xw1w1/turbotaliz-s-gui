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

/**
 * Built GUI class. Use [GuiManager] to build your own.
 * @see GuiBuilder
 * @see GuiManager
 * @since 1.1
 * @author xw1w1
 */
class Gui(
    private val rows: Int,
    private var name: Component,
    private val backgroundMaterial: (() -> Material)?,
    private val tiles: Int2ObjectOpenHashMap<Tile>
) {
    private var inventory: Inventory = Bukkit.createInventory(null, rows * 9, name)
    private val viewers = mutableSetOf<Player>()

    init {
        backgroundMaterial?.let { bg ->
            val bgItem = ItemStack(bg())
            for (i in 0 until inventory.size) {
                inventory.setItem(i, bgItem)
            }
        }

        tiles.values.forEach { tile ->
            inventory.setItem(tile.position, tile.itemStack())
        }
    }

    fun changeTitle(component: Component) {
        this.name = component
        this.update()
    }

    fun update() {
        this.viewers.forEach(::update)
    }

    fun update(player: Player) {
        val newInventory = Bukkit.createInventory(null, rows * 9, name)

        this.backgroundMaterial?.let { bg ->
            val bgItem = ItemStack(bg())
            for (i in 0 until newInventory.size) {
                newInventory.setItem(i, bgItem)
            }
        }

        this.tiles.values.forEach { tile -> newInventory.setItem(tile.position, tile.itemStack())}
        this.inventory = newInventory
        this.open(player)
    }

    fun open(player: Player) {
        viewers.add(player)
        player.openInventory(inventory)
    }

    fun close(player: Player) {
        viewers.remove(player)
        player.closeInventory(InventoryCloseEvent.Reason.PLUGIN)
    }

    fun containsViewer(player: Player): Boolean = this.viewers.contains(player)

    fun handleClick(slot: Int, player: Player, clickType: ClickType) = this.tiles[slot]?.click(player, clickType)
}