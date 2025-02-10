package me.michaelbrylevskii.sps.lib.core.util

@Suppress("NOTHING_TO_INLINE")
object CollectionUtil {
    object Extensions {
        val Collection<*>.indicesReversed: IntProgression
            get() = size - 1 downTo 0

        inline fun <T> Collection<T>.hasSingle(): Boolean = size == 1
        inline fun <T> Collection<T>.hasMultiple(): Boolean = size > 1

        inline fun <reified T> Collection<*>.firstOfTypeOrNull(): T? = firstOrNull { it is T } as T?
        inline fun <reified T> Collection<*>.firstOfType(): T = firstOfTypeOrNull()
            ?: throw NoSuchElementException("No element of type '${T::class.qualifiedName}' was found.")

        inline fun <T, R> Collection<T>.mapToHashSet(transform: (T) -> R): HashSet<R> =
            mapTo(HashSet(size), transform)

        inline fun <T, R> Collection<T>.mapToLinkedSet(transform: (T) -> R): LinkedHashSet<R> =
            mapTo(LinkedHashSet(size), transform)

        inline fun <T, R> Collection<T>.mapToSet(transform: (T) -> R): Set<R> =
            mapToLinkedSet(transform)

        inline fun <T> List<T>.forEachReversed(action: (T) -> Unit) {
            for (index in this.indicesReversed) action(get(index))
        }
    }
}
