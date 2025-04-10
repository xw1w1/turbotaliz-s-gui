package org.ttlzmc.gui

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.jetbrains.annotations.Contract
import java.util.*
import java.util.stream.Collectors

object ComponentFormatter {
    @Contract("_ -> new")
    fun single(vararg components: Component?): Component {
        return Component.join(JoinConfiguration.noSeparators(), components.asList())
    }

    @Contract("_ -> new")
    fun component(`object`: Any): Component {
        return if (`object` is Component) `object` else Component.text(`object`.toString())
    }

    @Contract("_ -> new")
    fun components(vararg objects: Any): Array<Component> {
        return Arrays.stream(objects).filter { Objects.nonNull(it) }
            .map { component(it) }.collect(Collectors.toList()).toTypedArray()
    }

    @Contract("_ -> new")
    fun newlined(vararg objects: Any): Component {
        return Component.join(JoinConfiguration.newlines(), *components(*objects))
    }

    fun newline(): Component {
        return Component.newline()
    }

    fun empty(): Component {
        return Component.empty()
    }

    @Contract("_ -> new")
    fun toString(vararg objects: Any): String {
        return PlainTextComponentSerializer.plainText().serialize(single(*components(*objects)))
    }

    @Contract("_, _, _ -> new")
    fun gradient(firstColor: String, secondColor: String, vararg objects: Any): Component {
        return MiniMessage.miniMessage().deserialize(
            "<gradient:#$firstColor:#$secondColor><content></gradient>",
            Placeholder.component("content", single(*components(*objects)))
        )
    }

    @Contract("_, _ -> new")
    fun hex(color: String, vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(TextColor.fromHexString(color))
    }

    @Contract("_ -> new")
    fun bold(vararg objects: Any): Component {
        return single(*components(*objects)).decorate(TextDecoration.BOLD)
    }

    @Contract("_ -> new")
    fun reset(vararg objects: Any): Component {
        return single(*components(*objects)).decoration(TextDecoration.ITALIC, false)
            .decoration(TextDecoration.BOLD, false).decoration(TextDecoration.UNDERLINED, false)
            .decoration(TextDecoration.STRIKETHROUGH, false).decoration(TextDecoration.OBFUSCATED, false)
    }

    @Contract("_ -> new")
    fun italic(vararg objects: Any): Component {
        return single(*components(*objects)).decorate(TextDecoration.ITALIC)
    }

    @Contract("_ -> new")
    fun strikethrough(vararg objects: Any): Component {
        return single(*components(*objects)).decorate(TextDecoration.STRIKETHROUGH)
    }

    @Contract("_ -> new")
    fun underlined(vararg objects: Any): Component {
        return single(*components(*objects)).decorate(TextDecoration.UNDERLINED)
    }

    @Contract("_ -> new")
    fun obfuscated(vararg objects: Any): Component {
        return single(*components(*objects)).decorate(TextDecoration.OBFUSCATED)
    }

    @Contract("_, _ -> new")
    fun hover(component: Component, vararg objects: Any): Component {
        return component.hoverEvent(HoverEvent.showText(single(*components(*objects))))
    }

    @Contract("_, _ -> new")
    fun openUrl(component: Component, link: String): Component {
        return component.clickEvent(ClickEvent.openUrl(link))
    }

    @Contract("_, _ -> new")
    fun suggestCommand(component: Component, command: String): Component {
        return component.clickEvent(ClickEvent.suggestCommand(command))
    }

    @Contract("_, _ -> new")
    fun runCommand(component: Component, command: String): Component {
        return component.clickEvent(ClickEvent.runCommand(command))
    }

    @Contract("_ -> new")
    fun red(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.RED)
    }

    @Contract("_ -> new")
    fun darkRed(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.DARK_RED)
    }

    @Contract("_ -> new")
    fun gold(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.GOLD)
    }

    @Contract("_ -> new")
    fun yellow(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.YELLOW)
    }

    @Contract("_ -> new")
    fun green(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.GREEN)
    }

    @Contract("_ -> new")
    fun darkGreen(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.DARK_GREEN)
    }

    @Contract("_ -> new")
    fun aqua(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.AQUA)
    }

    @Contract("_ -> new")
    fun darkAqua(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.DARK_AQUA)
    }

    @Contract("_ -> new")
    fun blue(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.BLUE)
    }

    @Contract("_ -> new")
    fun darkBlue(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.DARK_BLUE)
    }

    @Contract("_ -> new")
    fun lightPurple(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.LIGHT_PURPLE)
    }

    @Contract("_ -> new")
    fun darkPurple(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.DARK_PURPLE)
    }

    @Contract("_ -> new")
    fun gray(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.GRAY)
    }

    @Contract("_ -> new")
    fun darkGray(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.DARK_GRAY)
    }

    @Contract("_ -> new")
    fun black(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.BLACK)
    }

    @Contract("_ -> new")
    fun white(vararg objects: Any): Component {
        return single(*components(*objects)).colorIfAbsent(NamedTextColor.WHITE)
    }

    @Contract("_ -> new")
    fun text(vararg objects: Any): Component {
        return single(*components(*objects))
    }
}