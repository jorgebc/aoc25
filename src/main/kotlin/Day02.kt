fun main() {
    println("AoC day 02")

    var productIdRanges = readProductIdRanges("day02/example-input.txt")

    var invalidIdSum: Long
    invalidIdSum = solve(productIdRanges, ::isInvalidPartOne)
    check(invalidIdSum == 1227775554L) { "Part 1 example fail, sum should be 1227775554 but was $invalidIdSum" }

    productIdRanges = readProductIdRanges("day02/input.txt")
    invalidIdSum = solve(productIdRanges, ::isInvalidPartOne)
    println("Part 1 invalid ids sum: $invalidIdSum")

    productIdRanges = readProductIdRanges("day02/example-input.txt")
    invalidIdSum = solve(productIdRanges, ::isInvalidPartTwo)
    check(invalidIdSum == 4174379265L) { "Part 2 example fail, sum should be 4174379265 but was $invalidIdSum" }

    productIdRanges = readProductIdRanges("day02/input.txt")
    invalidIdSum = solve(productIdRanges, ::isInvalidPartTwo)
    println("Part 2 invalid ids sum: $invalidIdSum")
}

fun solve(productIdRanges: List<Pair<Long, Long>>, isInvalid: (Long) -> Boolean): Long {

    var invalidIdSum = 0L

    for (productIdRange in productIdRanges) {
        val (from, to) = productIdRange

        for (productId in from..to) {
            val isInvalid = isInvalid(productId)
            if (isInvalid) {
                invalidIdSum += productId
            }
        }
    }

    return invalidIdSum
}

fun isInvalidPartOne(productId: Long): Boolean {
    val (firstHalf, secondHalf) = splitInHalf(productId.toString())
    return firstHalf == secondHalf
}

fun isInvalidPartTwo(productId: Long): Boolean {
    val productIdString = productId.toString()
    val maxParts = productIdString.length

    for (numOfParts in 2..maxParts) {
        val parts = splitIn(productIdString, numOfParts)
        if (parts.toSet().size == 1) return true
    }

    return false
}

fun splitInHalf(input: String): Pair<String, String> {
    val mid = input.length / 2
    return input.substring(0, mid) to input.substring(mid)
}

fun splitIn(input: String, numOfParts: Int): List<String> {
    if (input.length % numOfParts != 0) return emptyList()
    else {
        val size = input.length / numOfParts
        return input.chunked(size)
    }
}

fun readProductIdRanges(filePath: String): List<Pair<Long, Long>> =
    readLines(filePath)
        .first()
        .split(",")
        .map { text ->
            text.split("-").let { Pair(it[0].toLong(), it[1].toLong()) }
        }


