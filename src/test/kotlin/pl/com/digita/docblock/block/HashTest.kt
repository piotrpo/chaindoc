package pl.com.digita.docblock.block

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class HashTest {

    @Test
    fun testEquals() {
        val a = Hash("hello".toByteArray())
        val b = Hash("hello".toByteArray())

        assertTrue(a == b)
    }

    @Test
    fun testHashCode() {
        val a = Hash("hello".toByteArray()).hashCode()
        val b = Hash("hello".toByteArray()).hashCode()

        assertTrue(a == b)
    }
}
