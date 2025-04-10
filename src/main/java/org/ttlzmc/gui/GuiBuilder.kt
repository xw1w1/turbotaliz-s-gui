package org.ttlzmc.gui

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.jetbrains.annotations.Range

/**
 * [Gui] builder. Contains simple methods to attach [Tile]s and configure gui's inventory.
 * @see Gui
 * @author xw1w1
 * @see 1.1
 */
class GuiBuilder(
    var title: Component = Component.empty(),
    private val rows: @Range(from = 1, to = 6) Int
) {
    private val tiles = Int2ObjectOpenHashMap<Tile>()
    private var backgroundFill: (() -> Material)? = null

    /**
     * Sets the background fill.
     *
     * This material will be applied first, just before tiles itemstacks.
     */
    fun backgroundFill(materialProvider: () -> Material) {
        this.backgroundFill = materialProvider
    }

    /**
     * Creates a new tile.
     * @param row tile's row
     * @param column tile's column
     * @param material tile's icon
     * @param block code block that should be applied to new tile
     */
    fun tile(
        row: @Range(from = 0, to = 5) Int,
        column: @Range(from = 0, to = 8) Int,
        material: Material,
        block: (Tile.() -> Unit)? = null
    ) {
        val tile = Tile(row, column, material)
        block?.invoke(tile)
        this.tiles.put(Tile.calculatePosition(tile), tile)
    }

    /**
     * Attaches a new tile to this GUI.
     * @param tile tile
     */
    fun tile(tile: Tile) = this.tiles.put(Tile.calculatePosition(tile), tile)

    fun build(): Gui = Gui(rows, title, backgroundFill, tiles)
}