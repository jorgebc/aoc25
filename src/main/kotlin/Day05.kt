fun main() {
    println("AoC day 05")

    var availableFreshIngredients = countAvailableFreshIngredients("day05/example-input.txt")
    var expectedAvailableIngredientsFresh = 3L
    check(availableFreshIngredients == expectedAvailableIngredientsFresh) { "Part 1 example fail, sum should be $expectedAvailableIngredientsFresh but was $availableFreshIngredients" }

    availableFreshIngredients = countAvailableFreshIngredients("day05/input.txt")
    println("Part 1 available fresh ingredients $availableFreshIngredients")

    availableFreshIngredients = countAllAvailableFreshIngredients("day05/example-input.txt")
    expectedAvailableIngredientsFresh = 14
    check(availableFreshIngredients == expectedAvailableIngredientsFresh) { "Part 2 example fail, sum should be $expectedAvailableIngredientsFresh but was $availableFreshIngredients" }

    availableFreshIngredients = countAllAvailableFreshIngredients("day05/input.txt")
    println("Part 2 available fresh ingredients $availableFreshIngredients")
}

fun countAllAvailableFreshIngredients(filePath: String): Long {

    val (freshIngredientsIdRanges, _) = readFromFile(filePath)
    val orderedFreshIngredientsIdRanges = orderIdRanges(freshIngredientsIdRanges)
    val compactedFreshIngredientsIdRanges = compactRanges(orderedFreshIngredientsIdRanges)
    return countFreshIngredients(compactedFreshIngredientsIdRanges)
}

fun countFreshIngredients(compactedFreshIngredientsIdRanges: MutableList<LongRange>) =
    compactedFreshIngredientsIdRanges.sumOf { it.last - it.first + 1 }

fun compactRanges(orderedFreshIngredientsIdRanges: List<LongRange>): MutableList<LongRange> {
    val compactedFreshIngredientsIdRanges = mutableListOf<LongRange>()
    compactedFreshIngredientsIdRanges.add(orderedFreshIngredientsIdRanges.first())

    for (next in orderedFreshIngredientsIdRanges.drop(1)) {
        val last = compactedFreshIngredientsIdRanges.last()
        if (notContained(next, last)) {
            compactedFreshIngredientsIdRanges.add(next)

        } else if (firstContained(next, last)) {
            val updatedRange = LongRange(last.first, next.last)
            val lastIndex = compactedFreshIngredientsIdRanges.indexOf(last)
            compactedFreshIngredientsIdRanges.removeAt(lastIndex)
            compactedFreshIngredientsIdRanges.add(updatedRange)

        } else if (lastContained(next, last)) {
            val updatedRange = LongRange(next.first, last.last)
            val lastIndex = compactedFreshIngredientsIdRanges.indexOf(last)
            compactedFreshIngredientsIdRanges.removeAt(lastIndex)
            compactedFreshIngredientsIdRanges.add(updatedRange)
        }
    }

    return compactedFreshIngredientsIdRanges
}

fun lastContained(range: LongRange, last: LongRange) =
    last.contains(range.last) && !last.contains(range.first)

fun firstContained(range: LongRange, last: LongRange) =
    last.contains(range.first) && !last.contains(range.last)

fun notContained(range: LongRange, last: LongRange) =
    !last.contains(range.first) && !last.contains(range.last)

fun orderIdRanges(freshIngredientsIdRanges: List<LongRange>) =
    freshIngredientsIdRanges.sortedBy { it.first }


fun countAvailableFreshIngredients(filePath: String): Long {
    var availableFreshIngredients = 0L

    val (freshIngredientsIdRanges, availableIngredientsIds) = readFromFile(filePath)

    for (id in availableIngredientsIds) {
        if (availableIngredientInRange(freshIngredientsIdRanges, id)) {
            availableFreshIngredients++
        }
    }

    return availableFreshIngredients
}

fun availableIngredientInRange(freshIngredientsIdRanges: List<LongRange>, ingredientId: Long) =
    freshIngredientsIdRanges.any { ingredientId in it }

fun readFromFile(filePath: String): Pair<List<LongRange>, List<Long>> {
    val lines = readLines(filePath)
    val index = lines.indexOfFirst { it == "" }

    val freshIngredientsIdRanges =
        lines.take(index).map { line -> line.split("-").let { LongRange(it[0].toLong(), it[1].toLong()) } }
    val availableIngredientsIds = lines.drop(index + 1).map { id -> id.toLong() }

    return Pair(freshIngredientsIdRanges, availableIngredientsIds)
}
