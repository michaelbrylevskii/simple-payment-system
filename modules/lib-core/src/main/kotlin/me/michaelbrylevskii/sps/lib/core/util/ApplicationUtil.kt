package me.michaelbrylevskii.sps.lib.core.util

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.addResourceOrFileSource
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.michaelbrylevskii.sps.lib.core.config.ServerProperties
import me.michaelbrylevskii.sps.lib.core.util.CollectionUtil.Extensions.forEachReversed
import kotlin.reflect.KClass

object ApplicationUtil {

    const val DEFAULT_FILE_PATH = "/properties.yml"

    private val logger = KotlinLogging.logger {}

    inline fun <reified T : Any> loadProperties(
        mainFilePath: String = DEFAULT_FILE_PATH,
        profiles: List<String> = EnvironmentUtil.getProfiles(),
    ): T = loadProperties(
        klass = T::class,
        mainFilePath = mainFilePath,
        profiles = profiles,
    )

    fun <T : Any> loadProperties(
        klass: KClass<T>,
        mainFilePath: String = DEFAULT_FILE_PATH,
        profiles: List<String> = EnvironmentUtil.getProfiles(),
    ): T {
        logger.info { "Loading application properties..." }
        val allPropertiesPaths = buildAllPaths(mainFilePath, profiles)
        logger.info { "Searching properties files: $allPropertiesPaths" }
        val configLoader = buildConfigLoader(allPropertiesPaths)
        val properties = configLoader.loadConfigOrThrow(klass, emptyList(), null)
        logger.info { "Loading application properties completed! Properties: \n$properties" }
        return properties
    }

    fun startApplication(
        serverProperties: ServerProperties,
        modulesLoader: Application.() -> Unit
    ) {
        logger.info { "Loading application..." }
        embeddedServer(
            factory = Netty,
            host = serverProperties.host,
            port = serverProperties.port
        ) {
            logger.info { "Loading application modules..." }
            modulesLoader()
            logger.info { "Loading application modules completed!" }
        }.start(wait = true)
    }

    private fun buildConfigLoader(
        propertiesPaths: List<String>,
    ): ConfigLoader = ConfigLoader.invoke {
        propertiesPaths.forEachReversed {
            addResourceOrFileSource(it, optional = true)
        }
    }

    private fun buildAllPaths(
        mainFilePath: String,
        profiles: List<String>,
    ): List<String> = buildList {
        add(mainFilePath)
        val pathWithoutExt = mainFilePath.substringBeforeLast('.')
        profiles.forEach { profile ->
            add(mainFilePath.replace(pathWithoutExt, "$pathWithoutExt-$profile"))
        }
    }
}
