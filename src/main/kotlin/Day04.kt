const val ROLL_OF_PAPER = '@'
const val EMPTY = '.'

fun main() {
    println("AoC day 04")

    var lines = readLines("day04/example-input.txt")
    var accessibleRolls = countAccessibleRolls(lines, false)
    var expectedAccessibleRolls = 13
    check(accessibleRolls == expectedAccessibleRolls) { "Part 1 example fail, sum should be $expectedAccessibleRolls but was $accessibleRolls" }

    lines = readLines("day04/input.txt")
    accessibleRolls = countAccessibleRolls(lines, false)
    println("Part 1 total accessible rolls of paper: $accessibleRolls")

    lines = readLines("day04/example-input.txt")
    accessibleRolls = countAccessibleRolls(lines, true)
    expectedAccessibleRolls = 43
    check(accessibleRolls == expectedAccessibleRolls) { "Part 2 example fail, sum should be $expectedAccessibleRolls but was $accessibleRolls" }

    lines = readLines("day04/input.txt")
    accessibleRolls = countAccessibleRolls(lines, true)
    println("Part 2 total accessible rolls of paper: $accessibleRolls")
}

fun countAccessibleRolls(lines: List<String>, withRemoval: Boolean): Int {

    var accessibleRolls = 0
    var rollsGridDiagram = readGridDiagram(lines)

    while (true) {
        val rollsToBeRemoved = ArrayList<Pair<Int, Int>>()

        for (row in rollsGridDiagram.indices) {
            for (col in rollsGridDiagram[row].indices) {

                if (isAccessible(rollsGridDiagram, row, col)) {
                    accessibleRolls++
                    rollsToBeRemoved.add(Pair(row, col))
                }

            }
        }

        if (rollsToBeRemoved.isEmpty() || !withRemoval) break
        rollsGridDiagram = removeRolls(rollsGridDiagram, rollsToBeRemoved)

    }

    return accessibleRolls
}

fun removeRolls(rollsGridDiagram: Array<CharArray>, rollsToBeRemoved: MutableList<Pair<Int, Int>>): Array<CharArray> {
    val copy = rollsGridDiagram.map { it.clone() }.toTypedArray()
    for (rollToBeRemoved in rollsToBeRemoved) {
        val (row, col) = rollToBeRemoved
        copy[row][col] = EMPTY
    }
    return copy
}

fun isAccessible(rollsGridDiagram: Array<CharArray>, row: Int, col: Int): Boolean {

    if (rollsGridDiagram[row][col] == EMPTY) {
        return false
    }

    return countAdjacentRolls(rollsGridDiagram, row, col) < 4
}

fun countAdjacentRolls(rollsGridDiagram: Array<CharArray>, row: Int, col: Int): Int {

    var adjacentRolls = 0

    adjacentRolls += checkAdjacent(rollsGridDiagram, row - 1, col - 1)
    adjacentRolls += checkAdjacent(rollsGridDiagram, row - 1, col)
    adjacentRolls += checkAdjacent(rollsGridDiagram, row - 1, col + 1)
    adjacentRolls += checkAdjacent(rollsGridDiagram, row, col - 1)
    adjacentRolls += checkAdjacent(rollsGridDiagram, row, col + 1)
    adjacentRolls += checkAdjacent(rollsGridDiagram, row + 1, col - 1)
    adjacentRolls += checkAdjacent(rollsGridDiagram, row + 1, col)
    adjacentRolls += checkAdjacent(rollsGridDiagram, row + 1, col + 1)

    return adjacentRolls
}

fun checkAdjacent(rollsGridDiagram: Array<CharArray>, row: Int, col: Int): Int {
    if (row !in rollsGridDiagram.indices) return 0
    if (col !in rollsGridDiagram[row].indices) return 0
    return if (rollsGridDiagram[row][col] == ROLL_OF_PAPER) 1 else 0
}

fun readGridDiagram(lines: List<String>) =
    lines
        .map { it.toCharArray() }
        .toTypedArray()
