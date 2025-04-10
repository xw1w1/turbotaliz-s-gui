package org.ttlzmc.gui

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack
import org.jetbrains.annotations.Range

@Suppress("MemberVisibilityCanBePrivate")
open class Tile(
    var row: @Range(from = 0, to = 5) Int,
    var column: @Range(from = 0, to = 8) Int,
    var icon: Material = Material.WHITE_CONCRETE,
    var title: Component = Component.empty(),
    var description: Component = Component.empty()
) {
    var clickAction: TileClickAction? = null

    open fun itemStack(): ItemStack {
        return ItemStack.of(icon).apply {
            val meta = this.itemMeta
            meta.itemName(this@Tile.title)
            meta.lore(listOf(this@Tile.description))
            this.itemMeta = meta
        }
    }

    open fun click(player: Player, type: ClickType) {
        this.clickAction?.onClick(player, this, type)
    }

    companion object {
        fun calculatePosition(tile: Tile): Int {
            return tile.row * 9 + tile.column
        }
    }
}