package org.ttlzmc.gui.lib

import com.google.errorprone.annotations.CanIgnoreReturnValue
import net.kyori.adventure.text.Component
import org.jetbrains.annotations.Range
import org.ttlzmc.gui.StyleUtils.gray
import org.ttlzmc.gui.StyleUtils.single
import java.util.LinkedList

/**
 * Represents a container containing from 1 to 32 GUI pages.
 *
 * @see GuiContainerPage
 * @see GuiManager
 */
class GuiContainer(
    mainTitle: Component?,
    private val size: @Range(from = 0, to = 32) Int
) {
    private val title: Component =
        if (mainTitle != null) single(mainTitle, gray(" - ")) else Component.empty()

    private val pages: MutableList<GuiContainerPage> = LinkedList<GuiContainerPage>()

    fun getTitle(gui: GuiContainerPage): Component = single(title, gui.title())

    fun pages(): List<GuiContainerPage> = pages

    fun addPage(pointer: @Range(from = 0, to = 32) Int, gui: GuiContainerPage) {
        if (checkSize(pointer)) return
        pages[pointer] = gui
    }

    @CanIgnoreReturnValue
    fun dispose(pointer: @Range(from = 0, to = 32) Int): GuiContainerPage {
        if (checkSize(pointer)) throw IndexOutOfBoundsException(
            "Can't dispose with pointer $pointer and maximum size $size"
        )
        else return pages.removeAt(pointer)
    }

    /**
     * @return **true** if and only if **value** is larger than the maximum container size
     */
    private fun checkSize(value: Int): Boolean {
        return size < value
    }

}