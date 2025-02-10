package me.michaelbrylevskii.sps.lib.core

import me.michaelbrylevskii.sps.lib.core.test.TestUtil.testImportantAction
import kotlin.test.Test
import kotlin.test.assertEquals

class SomeTest {

    @Test
    fun `some should work correctly`() {
        // Given
        val someNumber = 123
        val expected = "Test important result: 123!"

        // When
        val actual = testImportantAction(someNumber)

        // Then
        assertEquals(expected, actual)
    }
}
