import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class Day05Test {

    @Test
    fun `Part one should sum simple invalid ids, ids without 0`() {

        val (freshIngredientsIdRanges, availableIngredientsIds) = readFromFile("day05/example-input.txt")

        assertEquals(4, freshIngredientsIdRanges.size)
        assertEquals(6, availableIngredientsIds.size)
    }

    @Test
    fun `Order id ranges should not rearrange ids order`() {
        val freshIngredientsIdRanges = listOf(LongRange(1L, 2L), LongRange(2L, 3L))

        val orderedIdRanges = orderIdRanges(freshIngredientsIdRanges)

        val expectedOrderedIdRanges = listOf(Pair(1L, 2L), Pair(2L, 3L))
        assertContentEquals(
            expectedOrderedIdRanges.map { it.toList() },
            orderedIdRanges.map { it.toList() }
        )
    }

    @Test
    fun `Order id ranges should rearrange ids in ascending order`() {
        val freshIngredientsIdRanges = listOf(LongRange(2L, 3L), LongRange(1L, 2L))

        val orderedIdRanges = orderIdRanges(freshIngredientsIdRanges)

        val expectedOrderedIdRanges = listOf(Pair(1L, 2L), Pair(2L, 3L))
        assertContentEquals(
            expectedOrderedIdRanges.map { it.toList() },
            orderedIdRanges.map { it.toList() }
        )
    }

    @Test
    fun `First is contained if only the first is contained and not both`() {

        val range = LongRange(4L, 10L)
        val last = LongRange(1L, 16L)
        assertFalse { firstContained(range, last) }
    }

    @Test
    fun `First is contained if the first is contained not the last`() {

        val range = LongRange(1L, 15L)
        val last = LongRange(3L, 16L)
        assertFalse { firstContained(range, last) }
    }

    @Test
    fun `First is contained if only the first is contained and not the last`() {
        val range = LongRange(4L, 19L)
        val last = LongRange(1L, 16L)
        assertTrue { firstContained(range, last) }
    }

    @Test
    fun `Last is contained if only the last is contained and not both`() {

        val range = LongRange(6L, 10L)
        val last = LongRange(5L, 16L)
        assertFalse { lastContained(range, last) }
    }

    @Test
    fun `Last is contained if only the last is contained and not the first`() {

        val range = LongRange(6L, 18L)
        val last = LongRange(5L, 16L)
        assertFalse { lastContained(range, last) }
    }

    @Test
    fun `Last is contained if only the last is contained`() {

        val range = LongRange(4L, 15L)
        val last = LongRange(5L, 16L)
        assertTrue { lastContained(range, last) }
    }

    @Test
    fun `Is not contained if both are not contained`() {

        val range = LongRange(19L, 24L)
        val last = LongRange(5L, 16L)
        assertTrue { notContained(range, last) }
    }

    @Test
    fun `Is contained if both are contained`() {

        val range = LongRange(6L, 14L)
        val last = LongRange(5L, 16L)
        assertFalse { notContained(range, last) }
    }
}
