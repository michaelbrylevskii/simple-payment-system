package me.michaelbrylevskii.sps.lib.core.config

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.addResourceOrFileSource
import me.michaelbrylevskii.sps.lib.core.util.CollectionUtil.Extensions.forEachReversed
import kotlin.reflect.KClass

object ConfigReader {

    inline fun <reified T : Any> readConfig(
        path: String,
    ): T = readConfig(
        configKlass = T::class,
        path = path
    )

    inline fun <reified T : Any> readConfig(
        paths: List<String>,
    ): T = readConfig(
        configKlass = T::class,
        paths = paths
    )

    fun <T : Any> readConfig(
        configKlass: KClass<T>,
        path: String,
    ): T = readConfig(
        configKlass = configKlass,
        paths = listOf(path)
    )

    fun <T : Any> readConfig(
        configKlass: KClass<T>,
        paths: List<String>,
    ): T {
        val configLoader = buildConfigLoader(paths)
        return configLoader.loadConfigOrThrow(configKlass, emptyList(), null)
    }

    private fun buildConfigLoader(
        paths: List<String>,
    ): ConfigLoader = ConfigLoader.invoke {
        paths.forEachReversed { addResourceOrFileSource(it, optional = true) }
    }

}
