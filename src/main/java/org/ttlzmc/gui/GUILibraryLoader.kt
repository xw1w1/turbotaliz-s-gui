package org.ttlzmc.gui

import com.google.errorprone.annotations.CanIgnoreReturnValue
import org.bukkit.plugin.java.JavaPlugin
import org.ttlzmc.gui.lib.GuiManager

/**
 * Specify the main class that the library should refer to when the plugin is running.
 *
 * Simply call [GUILibraryLoader.init] in the main class of your plugin, and proceed.
 * @see GuiManager
 */
object GUILibraryLoader {
    private var instance: JavaPlugin? = null

    fun init(main: JavaPlugin) {
        checkInit()
        this.instance = main
        this.instance!!.server.pluginManager.registerEvents(
            GuiManager, this.instance!!
        )
    }

    fun instance(): JavaPlugin {
        return instance ?: throw RuntimeException("GUILibraryLoader is not initialized!")
    }

    @CanIgnoreReturnValue
    private fun checkInit(): Boolean {
        return (instance != null).also {
            if (!it) throw RuntimeException("GUILibraryLoader cannot be initialized twice!")
        }
    }
}