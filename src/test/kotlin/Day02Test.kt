package day02

import day02PartOne
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun `Part one should sum simple invalid ids, ids without 0`() {

        val productIdRanges = listOf(Pair(11L, 22L))
        val result = day02PartOne(productIdRanges)

        assertEquals(33, result)
    }
}
