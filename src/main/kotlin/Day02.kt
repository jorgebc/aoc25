fun main() {
    println("AoC day 02")

    var productIdRanges = readProductIdRanges("day02/example-input.txt")

    var invalidIdSum: Long
    invalidIdSum = day02PartOne(productIdRanges)
    check(invalidIdSum == 1227775554L) { "Part 1 example fail, sum should be 1227775554 but was $invalidIdSum" }

    productIdRanges = readProductIdRanges("day02/input.txt")
    invalidIdSum = day02PartOne(productIdRanges)
    println("Part 1 invalid ids sum: $invalidIdSum")
}

fun day02PartOne(productIdRanges: List<Pair<Long, Long>>): Long {

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

fun isInvalid(productId: Long): Boolean {
    val (firstHalf, secondHalf) = splitInHalf(productId.toString())
    return firstHalf == secondHalf
}

fun splitInHalf(input: String): Pair<String, String> {
    val mid = input.length / 2
    return input.substring(0, mid) to input.substring(mid)
}

fun readProductIdRanges(filePath: String): List<Pair<Long, Long>> =
    readLines(filePath)
        .first()
        .split(",")
        .map { text ->
            text.split("-").let { Pair(it[0].toLong(), it[1].toLong()) }
        }


