package org.ttlzmc.gui

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack
import org.jetbrains.annotations.Range

/**
 * A tile, one inventory slot. Can have a name, lore, and optionally an action when clicked.
 * @since 1.1
 * @author xw1w1
 */
open class Tile(
    row: @Range(from = 0, to = 5) Int,
    column: @Range(from = 0, to = 8) Int,
    var icon: Material = Material.WHITE_CONCRETE,
    var title: Component = Component.empty(),
    var description: Component = Component.empty()
) {
    var position = row * 9 + column

    private var clickActions: ArrayList<TileClickAction> = arrayListOf()

    open fun itemStack(): ItemStack {
        return ItemStack.of(icon).apply {
            val meta = this.itemMeta
            meta.itemName(this@Tile.title)
            meta.lore(listOf(this@Tile.description))
            this.itemMeta = meta
        }
    }

    open fun click(player: Player, type: ClickType) {
        this.clickActions.forEach {
            it.onClick(player, this, type)
        }
    }
}