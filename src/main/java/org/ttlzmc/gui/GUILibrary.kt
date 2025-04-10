package org.ttlzmc.gui

import com.google.errorprone.annotations.CanIgnoreReturnValue
import org.bukkit.plugin.java.JavaPlugin

/**
 * Specify the main class that the library should refer to when the plugin is running.
 *
 * Simply call [GUILibrary.init] in the main class of your plugin, and proceed.
 * @see GuiManager
 * @since 1.0
 * @author xw1w1
 */
object GUILibrary {
    private var instance: GuiManager? = null

    fun init(main: JavaPlugin) {
        checkInit()
        this.instance = GuiManager()
        main.server.pluginManager.registerEvents(this.instance!!.eventHandler, main)
    }

    fun getInstance(): GuiManager = instance ?: throw RuntimeException("GUILibraryLoader is not initialized!")

    @CanIgnoreReturnValue
    private fun checkInit(): Boolean {
        return (instance != null).also {
            if (!it) throw RuntimeException("GUILibraryLoader cannot be initialized twice!")
        }
    }
}