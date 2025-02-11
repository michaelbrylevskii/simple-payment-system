package me.michaelbrylevskii.sps.lib.jdbc.util

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import me.michaelbrylevskii.sps.lib.jdbc.config.DatabaseProperties
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import javax.sql.DataSource

object DatabaseUtil {

    fun configureDatabase(properties: DatabaseProperties): Database {
        val dataSource = configureDataSource(properties)
        val migrateResult = applyMigrations(dataSource)
        require(migrateResult.success) { "Migration failed." }
        val database = configureExposed(dataSource)
        return database
    }

    fun configureDataSource(properties: DatabaseProperties): DataSource {
        val config = HikariConfig().apply {
            if (properties.driverClassName != null) driverClassName = properties.driverClassName
            if (properties.url != null) jdbcUrl = properties.url
            if (properties.username != null) username = properties.username
            if (properties.password != null) password = properties.password.value
        }
        return HikariDataSource(config)
    }

    fun applyMigrations(dataSource: DataSource): MigrateResult {
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            //.table()
            .load()
        return flyway.migrate()
    }

    fun configureExposed(dataSource: DataSource): Database {
        return Database.connect(
            datasource = dataSource,
            databaseConfig = DatabaseConfig {
                // set other parameters here
            }
        )
    }

}
