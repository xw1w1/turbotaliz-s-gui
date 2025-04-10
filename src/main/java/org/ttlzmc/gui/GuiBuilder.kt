package org.ttlzmc.gui

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.jetbrains.annotations.Range

class GuiBuilder(
    var title: Component = Component.empty(),
    private val rows: @Range(from = 1, to = 6) Int
) {
    private val tiles = Int2ObjectOpenHashMap<Tile>()
    private var backgroundFill: (() -> Material)? = null

    fun backgroundFill(materialProvider: () -> Material) {
        this.backgroundFill = materialProvider
    }

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

    fun tile(tile: Tile) {
        this.tiles.put(Tile.calculatePosition(tile), tile)
    }

    fun build(): Gui {
        return Gui(rows, title, backgroundFill, tiles)
    }
}