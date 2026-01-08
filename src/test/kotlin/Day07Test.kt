import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    @Test
    fun `Beam should move from starting location empty space`() {
        val diagram = arrayOf(
            charArrayOf('.', 'S', '.'),
            charArrayOf('.', '.', '.')
        )
        val updatedDiagram = simulateBeamMovement(diagram)
        assertEquals('|', updatedDiagram[1][1])
    }

    @Test
    fun `Beam should split at splitter`() {
        val diagram = arrayOf(
            charArrayOf('.', '|', '.'),
            charArrayOf('.', '^', '.')
        )
        val updatedDiagram = simulateBeamMovement(diagram)
        assertEquals('|', updatedDiagram[1][0])
        assertEquals('|', updatedDiagram[1][2])
    }

    @Test
    fun `Beam should pass through empty space`() {
        val diagram = arrayOf(
            charArrayOf('.', '|', '.'),
            charArrayOf('.', '.', '.')
        )
        val updatedDiagram = simulateBeamMovement(diagram)
        assertEquals('|', updatedDiagram[1][1])
    }

    @Test
    fun `Count split should count split beam`() {
        val diagram = arrayOf(
            charArrayOf('.', '|', '.'),
            charArrayOf('.', '^', '.')
        )
        val count = countTimesBeamSplit(diagram)
        assertEquals(1, count)
    }

    @Test
    fun `Count split should not count not split beam`() {
        val diagram = arrayOf(
            charArrayOf('.', '.', '.'),
            charArrayOf('.', '^', '.')
        )
        val count = countTimesBeamSplit(diagram)
        assertEquals(0, count)
    }

    @Test
    fun `Count possible timelines should count single timeline`() {
        val diagram = arrayOf(
            charArrayOf('.', '|', '.'),
            charArrayOf('.', '.', '.')
        )
        val count = countPossibleTimelines(diagram, Pair(0, 1))
        assertEquals(1, count)
    }

    @Test
    fun `Count possible timelines should count two timelines`() {
        val diagram = arrayOf(
            charArrayOf('.', '|', '.'),
            charArrayOf('.', '^', '.'),
            charArrayOf('.', '.', '.')
        )
        val count = countPossibleTimelines(diagram, Pair(0, 1))
        assertEquals(2, count)
    }

    @Test
    fun `Count possible timelines should count three timelines`() {
        val diagram = arrayOf(
            charArrayOf('.', '|', '.', '.'),
            charArrayOf('.', '^', '.', '.'),
            charArrayOf('.', '.', '.', '.'),
            charArrayOf('.', '.', '^', '.'),
            charArrayOf('.', '.', '.', '.')
        )
        val count = countPossibleTimelines(diagram, Pair(0, 1))
        assertEquals(3, count)
    }

    @Test
    fun `Count possible timelines should count four timelines`() {
        val diagram = arrayOf(
            charArrayOf('.', '.', '.', '.', '.', '.', '|', '.', '.', '.', '.', '.', '.', '.', '.'),
            charArrayOf('.', '.', '^', '.', '.', '.', '^', '.', '.', '.', '.', '.', '^', '.', '.'),
            charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'),
            charArrayOf('.', '^', '.', '^', '.', '^', '.', '^', '.', '^', '.', '.', '.', '^', '.'),
            charArrayOf('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.')
        )
        val count = countPossibleTimelines(diagram, Pair(0, 6))
        assertEquals(4, count)
    }
}