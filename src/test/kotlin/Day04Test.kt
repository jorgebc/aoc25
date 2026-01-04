import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Day04Test {

    @Test
    fun `Read grid diagram should map 3x3 grid`() {

        val lines = listOf("...", "...", "...")
        val gridDiagram = readGridDiagram(lines)

        val expectedGrid = Array(3) { CharArray(3) { '.' } }
        assertContentEquals(
            expectedGrid.map { it.toList() },
            gridDiagram.map { it.toList() }
        )
    }

    @Test
    fun `Check top left empty should return 0`() {

        val lines = listOf("...", "...", "...")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = checkAdjacent(gridDiagram, 0, 0)

        assertEquals(0, adjacent)
    }

    @Test
    fun `Check top left not empty should return 1`() {

        val lines = listOf("@@@", "@@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = checkAdjacent(gridDiagram, 0, 0)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Check negative row should return 0`() {
        val lines = listOf("@@@", "@@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = checkAdjacent(gridDiagram, -1, 0)

        assertEquals(0, adjacent)
    }

    @Test
    fun `Check negative col should return 0`() {
        val lines = listOf("@@@", "@@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = checkAdjacent(gridDiagram, 0, -1)

        assertEquals(0, adjacent)
    }

    @Test
    fun `Check out of bound row should return 0`() {
        val lines = listOf("@@@", "@@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = checkAdjacent(gridDiagram, 3, 0)

        assertEquals(0, adjacent)
    }

    @Test
    fun `Check out of bound col should return 0`() {
        val lines = listOf("@@@", "@@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = checkAdjacent(gridDiagram, 0, 3)

        assertEquals(0, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count top left`() {
        val lines = listOf("@..", ".@.", "...")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count top`() {
        val lines = listOf(".@.", ".@.", "...")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count top right`() {
        val lines = listOf("..@", ".@.", "...")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count left`() {
        val lines = listOf("...", "@@.", "...")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count right`() {
        val lines = listOf("...", ".@@", "...")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count bottom left`() {
        val lines = listOf("...", ".@.", "@..")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count bottom`() {
        val lines = listOf("...", ".@.", ".@.")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should count bottom right`() {
        val lines = listOf("...", ".@.", "..@")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(1, adjacent)
    }

    @Test
    fun `Count adjacent rolls should not count empty`() {
        val lines = listOf("...", ".@.", "...")
        val gridDiagram = readGridDiagram(lines)
        val adjacent = countAdjacentRolls(gridDiagram, 1, 1)

        assertEquals(0, adjacent)
    }

    @Test
    fun `Roll should be accessible if has 0 adjacent`() {
        val lines = listOf("...", ".@.", "...")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(true, isAccessible)
    }

    @Test
    fun `Roll should be accessible if has 1 adjacent`() {
        val lines = listOf("...", ".@.", ".@.")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(true, isAccessible)
    }

    @Test
    fun `Roll should be accessible if has 2 adjacent`() {
        val lines = listOf("@..", ".@.", ".@.")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(true, isAccessible)
    }

    @Test
    fun `Roll should be accessible if has 3 adjacent`() {
        val lines = listOf("@..", ".@@", ".@.")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(true, isAccessible)
    }

    @Test
    fun `Roll should not be accessible if has 4 adjacent`() {
        val lines = listOf("@@.", ".@@", ".@.")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(false, isAccessible)
    }

    @Test
    fun `Roll should not be accessible if has 5 adjacent`() {
        val lines = listOf("@@.", ".@@", "@@.")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(false, isAccessible)
    }

    @Test
    fun `Roll should not be accessible if has 6 adjacent`() {
        val lines = listOf("@@.", ".@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(false, isAccessible)
    }

    @Test
    fun `Roll should not be accessible if has 7 adjacent`() {
        val lines = listOf("@@.", "@@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(false, isAccessible)
    }

    @Test
    fun `Roll should not be accessible if has 8 adjacent`() {
        val lines = listOf("@@@", "@@@", "@@@")
        val gridDiagram = readGridDiagram(lines)
        val isAccessible = isAccessible(gridDiagram, 1, 1)

        assertEquals(false, isAccessible)
    }

    @Test
    fun `Remove roll should only remove the specified element`() {
        val lines = listOf("...", ".@.", "...")
        val gridDiagram = readGridDiagram(lines)
        val updatedGridDiagram = removeRolls(gridDiagram, mutableListOf(Pair(1, 1)))

        val expectedGrid = Array(3) { CharArray(3) { '.' } }
        assertContentEquals(
            expectedGrid.map { it.toList() },
            updatedGridDiagram.map { it.toList() }
        )
    }
}
