package me.michaelbrylevskii.sps.common.config

import com.sksamuel.hoplite.Masked

data class DatabaseProperties(
    val url: String,
    val user: String,
    val password: Masked
)
