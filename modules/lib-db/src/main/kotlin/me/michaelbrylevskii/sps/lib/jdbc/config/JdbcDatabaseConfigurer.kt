package me.michaelbrylevskii.sps.lib.jdbc.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import me.michaelbrylevskii.sps.lib.jdbc.config.model.JdbcDatabaseConfig
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import javax.sql.DataSource

object JdbcDatabaseConfigurer {

    fun Application.configureJdbcDatabase(config: JdbcDatabaseConfig): Database {
        val dataSource = configureDataSource(config)
        val migrateResult = applyMigrations(dataSource)
        require(migrateResult.success) { "Migration failed." }
        val database = configureExposed(dataSource)
        return database
    }

    fun configureDataSource(config: JdbcDatabaseConfig): DataSource {
        val config = HikariConfig().apply {
            if (config.driverClassName != null) driverClassName = config.driverClassName
            if (config.url != null) jdbcUrl = config.url
            if (config.username != null) username = config.username
            if (config.password != null) password = config.password.value
        }
        return HikariDataSource(config)
    }

    fun applyMigrations(dataSource: DataSource): MigrateResult {
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            //.table() // TODO: add more properties for flyway
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
