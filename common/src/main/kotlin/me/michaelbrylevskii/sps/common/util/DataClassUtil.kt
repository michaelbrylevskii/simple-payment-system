package me.michaelbrylevskii.sps.common.util

import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

object DataClassUtil {
    object Extensions {

        fun <T : Any> T.equalsHelper(other: Any?, vararg props: KProperty1<T, *>): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            for (prop in props) {
                @Suppress("UNCHECKED_CAST")
                if (prop.get(this) != prop.get(other as T)) return false
            }

            return true
        }

        fun <T : Any> T.hashCodeHelper(vararg props: KProperty1<T, *>): Int {
            return props.fold(31) { acc, prop -> acc * prop.get(this).hashCode() }
        }

        fun <T : Any> T.toStringHelper(vararg props: KProperty1<T, *>): String {
            return buildString {
                append(this::class.simpleName)
                append("(")
                props.joinToString() { prop ->
                    "${prop.name} = ${prop.get(this@toStringHelper).toString()}"
                }
                append(")")
            }
        }

        fun <T : Any> T.equalsExclusionHelper(other: Any?, exclude: Set<KProperty1<T, *>>): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            @Suppress("UNCHECKED_CAST")
            val props = (this::class.declaredMemberProperties - exclude) as Collection<KProperty1<T, *>>

            for (prop in props) {
                @Suppress("UNCHECKED_CAST")
                if (prop.get(this) != prop.get(other as T)) return false
            }

            return true
        }

        fun <T : Any> T.hashCodeExclusionHelper(exclude: Set<KProperty1<T, *>>): Int {
            @Suppress("UNCHECKED_CAST")
            val props = (this::class.declaredMemberProperties - exclude) as Collection<KProperty1<T, *>>

            return props.fold(31) { acc, prop -> acc * prop.get(this).hashCode() }
        }

        fun <T : Any> T.toStringExclusionHelper(exclude: Set<KProperty1<T, *>>): String {
            @Suppress("UNCHECKED_CAST")
            val props = (this::class.declaredMemberProperties - exclude) as Collection<KProperty1<T, *>>

            return buildString {
                append(this::class.simpleName)
                append("(")
                props.joinToString() { prop ->
                    "${prop.name} = ${prop.get(this@toStringExclusionHelper).toString()}"
                }
                append(")")
            }
        }

    }
}
