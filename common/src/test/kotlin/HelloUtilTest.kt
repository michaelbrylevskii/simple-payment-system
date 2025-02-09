package me.michaelbrylevskii.sps.common

import kotlin.test.Test
import kotlin.test.assertEquals

class SomeTest {

    @Test
    fun `some should work correctly`() {
        // Given
        val expected = 123

        // When
        val actual = 100 + 20 + 3

        // Then
        assertEquals(expected, actual)
    }
}
