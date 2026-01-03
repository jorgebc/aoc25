import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun `Part one should sum simple invalid ids, ids without 0`() {

        val productIdRanges = listOf(Pair(11L, 22L))
        val result = solve(productIdRanges, ::isInvalidPartOne)

        assertEquals(33, result)
    }

    @Test
    fun `Part two should sum if number repeats three times`() {

        val productIdRanges = listOf(Pair(99L, 111L))
        val result = solve(productIdRanges, ::isInvalidPartTwo)

        assertEquals(210, result)
    }
}
