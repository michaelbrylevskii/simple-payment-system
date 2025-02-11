package me.michaelbrylevskii.sps.lib.core

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.michaelbrylevskii.sps.lib.core.config.ConfigReader.readConfig
import me.michaelbrylevskii.sps.lib.core.config.model.CoreApplicationConfig
import me.michaelbrylevskii.sps.lib.core.config.model.ServerConfig
import me.michaelbrylevskii.sps.lib.core.util.EnvironmentUtil.getProfiles
import kotlin.reflect.KClass

object ApplicationStarter {

    const val DEFAULT_CONFIG_PATH = "/app-config.yml"

    private val logger = KotlinLogging.logger {}

    inline fun <reified T : CoreApplicationConfig> startApplication(
        blockingMode: Boolean = true,
        configPath: String = DEFAULT_CONFIG_PATH,
        noinline modulesLoader: Application.(config: T) -> Unit,
    ) = startApplication(T::class, blockingMode, configPath, modulesLoader)

    fun <T : CoreApplicationConfig> startApplication(
        configKlass: KClass<T>,
        blockingMode: Boolean = true,
        configPath: String = DEFAULT_CONFIG_PATH,
        modulesLoader: Application.(config: T) -> Unit,
    ): EmbeddedServer<out ApplicationEngine, out ApplicationEngine.Configuration> {
        val config = loadConfig(configKlass, configPath)
        return startServer(config.server, blockingMode) { modulesLoader(config) }
    }

    inline fun <reified T : CoreApplicationConfig> loadConfig(
        configPath: String = DEFAULT_CONFIG_PATH,
    ) = loadConfig(T::class, configPath)

    fun <T : CoreApplicationConfig> loadConfig(
        configKlass: KClass<T>,
        configPath: String = DEFAULT_CONFIG_PATH,
    ): T {
        val profiles = getProfiles()
        logger.info { "Active profiles: ${profiles.joinToString()}." }
        val allConfigPaths = buildAllConfigPaths(configPath, profiles)
        logger.info { "Applicable configs: ${allConfigPaths.joinToString()}." }
        return readConfig(configKlass, allConfigPaths)
    }

    fun startServer(
        serverConfig: ServerConfig,
        blockingMode: Boolean = true,
        modulesLoader: Application.() -> Unit,
    ): EmbeddedServer<out ApplicationEngine, out ApplicationEngine.Configuration> {
        logger.info { "Starting server..." }
        return embeddedServer(
            factory = Netty,
            host = serverConfig.host,
            port = serverConfig.port
        ) {
            logger.info { "Loading modules..." }
            modulesLoader()
            logger.info { "Loading modules completed!" }
        }.start(wait = blockingMode)
    }

    private fun buildAllConfigPaths(
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
