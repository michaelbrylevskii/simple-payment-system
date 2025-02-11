package me.michaelbrylevskii.sps.app.accounts

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import me.michaelbrylevskii.sps.app.accounts.ApplicationStarter.loadModules
import me.michaelbrylevskii.sps.app.accounts.config.ApplicationConfig
import me.michaelbrylevskii.sps.lib.core.ApplicationStarter.loadConfig
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            val config = loadConfig<ApplicationConfig>()
            loadModules(config)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}
