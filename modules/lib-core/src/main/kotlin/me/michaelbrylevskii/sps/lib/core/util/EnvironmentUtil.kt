package me.michaelbrylevskii.sps.lib.core.util

object EnvironmentUtil {
    const val PROFILES_PROPERTY = "profiles"
    const val DEFAULT_PROFILE = "local"

    fun getSingleOrNull(name: String): String? {
        val nameVariants = setOf(name, name.uppercase(), name.lowercase())
        val value = nameVariants.firstNotNullOfOrNull { System.getenv(it) }
            ?: nameVariants.firstNotNullOfOrNull { System.getProperty(it) }
        return value?.trim()
    }

    fun getSingle(name: String): String =
        checkNotNull(getSingleOrNull(name)) { "Environment property '$name' is missing!" }

    fun getListOrNull(name: String, delimiter: String = ","): List<String>? {
        val values = getSingleOrNull(name)?.split(delimiter)
        return values?.map { it.trim() }?.filter { it.isNotBlank() }
    }

    fun getList(name: String, delimiter: String = ","): List<String> =
        checkNotNull(getListOrNull(name, delimiter)) { "Environment property '$name' is missing!" }

    fun getProfiles(): List<String> =
        getListOrNull(PROFILES_PROPERTY) ?: listOf(DEFAULT_PROFILE)

}
