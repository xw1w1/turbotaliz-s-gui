package org.ttlzmc.gui.lib.tile

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.ttlzmc.gui.lib.GuiContainerPage

/**
 * Represents a single tile in [GuiContainerPage].
 *
 * Tile contains its own display name, description, and click actions
 *
 * @param icon Material that will be displayed in the GUI
 * @param title Display name of this tile, will be visible when hover over it
 */
class Tile(
    private var icon: Material,
    private var title: Component,
    private val parent: GuiContainerPage
) {
    private val tile = ItemStack.of(icon, 1)
    private val clickActions: ArrayList<TileClickAction> = ArrayList()

    init {
        tile.editMeta { it.displayName(title) }
    }

    fun icon() = icon
    fun icon(icon: Material) { this.icon = icon }

    fun parent() = parent

    fun itemStack(): ItemStack = tile

    fun description(): MutableList<Component>? = tile.lore()
    fun description(list: ArrayList<Component>) = tile.editMeta { it.lore(list) }

    fun addClickAction(action: TileClickAction) = this.clickActions.add(action)

    fun click(player: Player) = clickActions.forEach { it.onClick(player, this) }

}