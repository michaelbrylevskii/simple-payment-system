package me.michaelbrylevskii.sps.common.util

import com.sksamuel.hoplite.ConfigLoader
import com.sksamuel.hoplite.addResourceOrFileSource
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.michaelbrylevskii.sps.common.config.ServerProperties

object ApplicationUtil {

    const val DEFAULT_FILE_PATH = "/application.yml"

    @PublishedApi
    internal val logger = KotlinLogging.logger {}

    inline fun <reified T : Any> loadProperties(
        filePath: String = DEFAULT_FILE_PATH,
        profiles: List<String> = EnvironmentUtil.getProfiles(),
    ): T {
        logger.info { "Loading application properties..." }
        val configLoader = buildConfigLoader(filePath, profiles)
        val properties = configLoader.loadConfigOrThrow<T>()
        logger.info { "Loading application properties completed! Properties: \n$properties" }
        return properties
    }

    fun startApplication(
        serverProperties: ServerProperties,
        modulesLoader: Application.() -> Unit
    ) {
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

    @PublishedApi
    internal fun buildConfigLoader(
        filePath: String,
        profiles: List<String>,
    ): ConfigLoader = ConfigLoader.invoke {
        val pathWithoutExt = filePath.substringBeforeLast('.')
        // addEnvironmentSource()
        // withEnvironment() // TODO: learn
        // addResourceSource(filePath)
        addResourceOrFileSource(filePath)
        profiles.forEach { profile ->
            val profileFileName = filePath.replace(
                oldValue = pathWithoutExt,
                newValue = "$pathWithoutExt-$profile"
            )
            addResourceOrFileSource(profileFileName, optional = true)
        }
    }
}
