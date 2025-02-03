package org.ttlzmc.gui.lib

import com.google.common.collect.Sets
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit.createInventory
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.jetbrains.annotations.Range
import org.ttlzmc.gui.GUILibraryLoader
import org.ttlzmc.gui.lib.tile.Tile
import java.util.*

/**
 * Represents a single view in [GuiContainer].
 */
abstract class GuiContainerPage(
    private var title: Component,
    rows: @Range(from = 1, to = 6) Int
) {
    private val inventory: Inventory = createInventory(null, rows * 9, title)
    private val tiles: Int2ObjectOpenHashMap<Tile> = Int2ObjectOpenHashMap()

    private val viewers: HashSet<UUID> = Sets.newHashSet()

    fun title(): Component = title
    fun title(title: Component) { this.title = title }

    fun containsViewer(id: UUID) = viewers.contains(id)
    fun containsViewer(player: Player) = viewers.contains(player.uniqueId)

    fun show(player: Player) {
        player.scheduler.run(GUILibraryLoader.instance(), {
            this.viewers.add(player.uniqueId)
            player.openInventory(inventory)
        }, { this.viewers.remove(player.uniqueId) })
    }

    fun close(player: Player) {
        player.scheduler.run(GUILibraryLoader.instance(), {
            this.viewers.remove(player.uniqueId)
            val inventory = player.openInventory
            if (inventory.type != InventoryType.CRAFTING
                && inventory.topInventory == this.inventory
            ) {
                player.closeInventory()
            }
        }, { this.viewers.remove(player.uniqueId) })
    }

    fun setTile(
        row: @Range(from = 0, to = 5) Int,
        column: @Range(from = 0, to = 8) Int,
        tile: Tile
    ) {
        this.tiles[row * 9 + column] = tile
    }

    fun setTiles(rows: IntRange, columns: IntRange, tile: Tile) {
        for (row in rows) {
            for (column in columns) {
                this.setTile(row, column, tile)
            }
        }
    }

    fun fill(tile: Tile) {
        this.setTiles(0..5, 0..8, tile)
    }

    fun click(slot: Int, player: Player) {
        tiles[slot]?.click(player)
    }

    abstract fun update()
}