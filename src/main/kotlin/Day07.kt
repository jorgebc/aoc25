const val STARTING_POSITION = 'S'
const val EMPTY_SPACE = '.'
const val SPLITTER = '^'
const val BEAM = '|'

fun main() {
    println("AoC day 07")

    var totalSplits = calculateTotalSplits("day07/example-input.txt")
    val expectedTotalSplits = 21
    check(totalSplits == expectedTotalSplits) { "Part 1 example fail, split should be $expectedTotalSplits but was $totalSplits" }

    totalSplits = calculateTotalSplits("day07/input.txt")
    println("Part 1 total splits $totalSplits")

    var totalTimelines = calculateTotalTimelines("day07/example-input.txt")
    val expectedTotalTimelines = 40L
    check(expectedTotalTimelines == totalTimelines) { "Part 2 example fail, total should be $expectedTotalTimelines but was $totalTimelines" }

    totalTimelines = calculateTotalTimelines("day07/input.txt")
    println("Part 2 total timelines $totalTimelines")
}

fun calculateTotalTimelines(filePath: String): Long {
    val lines = readLines(filePath)
    val diagram = createDiagram(lines)
    val startPosition = locateStartPosition(diagram)
    return countPossibleTimelines(diagram, startPosition)
}

fun locateStartPosition(diagram: Array<CharArray>): Pair<Int, Int> {
    for ((row, line) in diagram.withIndex()) {
        for ((col, char) in line.withIndex()) {
            if (char == STARTING_POSITION) return row to col
        }
    }
    error("Starting position not found")
}

fun calculateTotalSplits(filePath: String): Int {

    val lines = readLines(filePath)
    val diagram = createDiagram(lines)
    val updatedDiagram = simulateBeamMovement(diagram)
    return countTimesBeamSplit(updatedDiagram)
}

fun countTimesBeamSplit(diagram: Array<CharArray>): Int {
    var split = 0

    for ((row, line) in diagram.withIndex()) {
        for ((col, char) in line.withIndex()) {
            if (char == SPLITTER && diagram[row - 1][col] == BEAM) split++
        }
    }

    return split
}

fun countPossibleTimelines(
    diagram: Array<CharArray>,
    beamPos: Pair<Int, Int>,
    memo: MutableMap<Pair<Int, Int>, Long> = mutableMapOf()
): Long {

    memo[beamPos]?.let { return it }

    var timelines = 1L
    val startRow = beamPos.first
    var beamCol = beamPos.second

    for (row in startRow..<diagram.size - 1) {

        val belowCell = diagram[row + 1][beamCol]
        if (belowCell == SPLITTER) {
            val splitRight = Pair(row + 1, beamCol + 1)
            timelines += countPossibleTimelines(diagram, splitRight, memo)
            beamCol -= 1
        }
    }

    memo[beamPos] = timelines

    return timelines
}

fun simulateBeamMovement(diagram: Array<CharArray>): Array<CharArray> {
    for ((row, line) in diagram.withIndex()) {
        for ((col, char) in line.withIndex()) {

            if (char == STARTING_POSITION) {
                diagram[row + 1][col] = BEAM

            } else if (char == BEAM && (row + 1 in diagram.indices)) {
                if (diagram[row + 1][col] == EMPTY_SPACE) diagram[row + 1][col] = BEAM
                else if (diagram[row + 1][col] == SPLITTER) {
                    diagram[row + 1][col - 1] = BEAM
                    diagram[row + 1][col + 1] = BEAM
                }
            }
        }
        println(line.concatToString())
    }
    return diagram
}

fun createDiagram(lines: List<String>): Array<CharArray> =
    lines.map { it.toCharArray() }.toTypedArray()
