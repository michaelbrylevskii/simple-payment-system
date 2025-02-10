package me.michaelbrylevskii.sps.accounts

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import me.michaelbrylevskii.sps.accounts.ApplicationStarter.loadModules
import me.michaelbrylevskii.sps.accounts.config.ApplicationProperties
import me.michaelbrylevskii.sps.common.util.ApplicationUtil.loadProperties
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            val properties = loadProperties<ApplicationProperties>()
            loadModules(properties)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}
