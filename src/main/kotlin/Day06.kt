fun main() {
    println("AoC day 06")

    var grandTotal = calculateGrandTotal("day06/example-input.txt", ::extractOperandsHorizontally)
    var expectedGrandTotal = 4277556L
    check(grandTotal == expectedGrandTotal) { "Part 1 example fail, sum should be $expectedGrandTotal but was $grandTotal" }

    grandTotal = calculateGrandTotal("day06/input.txt", ::extractOperandsHorizontally)
    println("Part 1 grand total $grandTotal")

    grandTotal = calculateGrandTotal("day06/example-input.txt", ::extractOperandsVertically)
    expectedGrandTotal = 3263827L
    check(grandTotal == expectedGrandTotal) { "Part 2 example fail, sum should be $expectedGrandTotal but was $grandTotal" }

    grandTotal = calculateGrandTotal("day06/input.txt", ::extractOperandsVertically)
    println("Part 2 grand total $grandTotal")

}

fun calculateGrandTotal(
    filePath: String,
    extractOperands: (operandsLines: List<String>) -> List<List<Int>>
): Long {
    val lines = readLines(filePath)
    val operators = extractOperators(lines.last())
    val operands = extractOperands(lines.dropLast(1))

    val partialResults = solveOperations(operands, operators)
    return partialResults.sumOf { it }
}

fun solveOperations(operandsLists: List<List<Int>>, operators: List<String>) =
    operandsLists.mapIndexed { index, operands -> solveOperation(operators[index], operands) }

fun solveOperation(operator: String, operands: List<Int>) =
    when (operator) {
        "+" -> operands.sumOf { it.toLong() }
        "*" -> operands.fold(1L) { acc, value ->
            acc * value
        }

        else -> error("Unknown operator: $operator")
    }

fun extractOperandsVertically(operandsLines: List<String>): List<List<Int>> {

    val splitLines = operandsLines.map { it.chunked(1) }
    val operandsList: MutableList<MutableList<Int>> = mutableListOf()

    val cols = splitLines.first().size

    var operands = mutableListOf<Int>()

    for (col in 0 until cols) {

        if (emptyCol(splitLines, col)) {
            operandsList.add(operands)
            operands = mutableListOf()

        } else {
            val operand = extractOperandVerticallyAt(splitLines, col)
            operands.add(operand.toInt())
        }

    }

    operandsList.add(operands)


    return operandsList
}

fun emptyCol(splitLines: List<List<String>>, col: Int) =
    splitLines.all { it[col] == " " }

fun extractOperandVerticallyAt(splitLines: List<List<String>>, col: Int): String {

    val rows = splitLines.size
    val operand = StringBuilder()

    for (row in 0 until rows) {
        val digit = splitLines[row][col]
        if (digit != " ") operand.append(digit)
    }

    return operand.toString()
}

fun extractOperandsHorizontally(operandsLines: List<String>): List<List<Int>> {

    val cleanedOperands = operandsLines.map { cleanSpaces(it).split(" ") }
    val rows = operandsLines.size
    val cols = cleanedOperands.first().size

    val operandsList: MutableList<MutableList<Int>> = mutableListOf()


    for (col in 0 until cols) {
        val operands = mutableListOf<Int>()
        for (rows in 0 until rows) {
            val operand = cleanedOperands[rows][col].toInt()
            operands.add(operand)
        }
        operandsList.add(operands)
    }

    return operandsList
}

fun extractOperators(operatorsLine: String): List<String> =
    cleanSpaces(operatorsLine)
        .split(" ")

fun cleanSpaces(line: String) =
    line.replace(" +".toRegex(), " ")
        .trim()