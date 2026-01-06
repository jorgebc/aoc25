import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day06Test {

    @Test
    fun `Extract operations should return a list of operations`() {
        val line = "*   +   *   +  "
        val operands = extractOperators(line)
        assertEquals(listOf("*", "+", "*", "+"), operands)
    }

    @Test
    fun `Extract operands horizontally should extract horizontally a list of numbers`() {
        val operandsLines = listOf(
            "123 328",
            " 45 64 "
        )
        val operands = extractOperandsHorizontally(operandsLines)
        val expectedOperands = listOf(listOf(123, 45), listOf(328, 64))
        assertContentEquals(
            expectedOperands.map { it.toList() },
            operands.map { it.toList() }
        )
    }

    @Test
    fun `Extract operands vertically one digit should extract vertically a list of numbers`() {
        val operandsLines = listOf(
            "1"
        )
        val operands = extractOperandsVertically(operandsLines)
        val expectedOperands = listOf(listOf(1))
        assertContentEquals(
            expectedOperands.map { it.toList() },
            operands.map { it.toList() }
        )
    }

    @Test
    fun `Extract operands vertically two digits should extract vertically a list of numbers`() {
        val operandsLines = listOf(
            "1",
            "2"
        )
        val operands = extractOperandsVertically(operandsLines)
        val expectedOperands = listOf(listOf(12))
        assertContentEquals(
            expectedOperands.map { it.toList() },
            operands.map { it.toList() }
        )
    }

    @Test
    fun `Extract operands vertically two digits with space above should extract vertically a list of numbers`() {
        val operandsLines = listOf(
            " ",
            "1",
            "2"
        )
        val operands = extractOperandsVertically(operandsLines)
        val expectedOperands = listOf(listOf(12))
        assertContentEquals(
            expectedOperands.map { it.toList() },
            operands.map { it.toList() }
        )
    }

    @Test
    fun `Extract operands vertically two digits with space below should extract vertically a list of numbers`() {
        val operandsLines = listOf(
            "1",
            "2",
            " "
        )
        val operands = extractOperandsVertically(operandsLines)
        val expectedOperands = listOf(listOf(12))
        assertContentEquals(
            expectedOperands.map { it.toList() },
            operands.map { it.toList() }
        )
    }

    @Test
    fun `Extract operands vertically two operands should extract vertically a list of numbers`() {
        val operandsLines = listOf(
            "12",
            "21"
        )
        val operands = extractOperandsVertically(operandsLines)
        val expectedOperands = listOf(listOf(12, 21))
        assertContentEquals(
            expectedOperands.map { it.toList() },
            operands.map { it.toList() }
        )
    }

    @Test
    fun `Empty column check should return false`() {
        val operandsLines = listOf(
            listOf("1"," ", "2"),
            listOf("2"," ", "1")
        )
        assertFalse(emptyCol(operandsLines, 0))
    }

    @Test
    fun `Empty column check should return true`() {
        val operandsLines = listOf(
            listOf("1"," ", "2"),
            listOf("2"," ", "1")
        )
        assertTrue(emptyCol(operandsLines, 1))
    }

    @Test
    fun `Extract operands vertically should extract horizontally a list of numbers`() {
        val line = listOf(
            "123 328",
            " 45 64 "
        )
        val operands = extractOperandsVertically(line)
        val expectedOperands = listOf(listOf(1, 24, 35), listOf(36, 24, 8))
        assertContentEquals(
            expectedOperands.map { it.toList() },
            operands.map { it.toList() }
        )
    }
}