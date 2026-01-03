fun main() {
    println("AoC day 03")

    var batteryBanks = readBatteryBanks("day03/example-input.txt")
    var totalOutputJoltge = sumMaximumJoltagesPart1(batteryBanks)
    check(totalOutputJoltge == 357) { "Part 1 example fail, sum should be 357 but was $totalOutputJoltge" }

    batteryBanks = readBatteryBanks("day03/input.txt")
    totalOutputJoltge = sumMaximumJoltagesPart1(batteryBanks)
    println("Part 1 total output joltge: $totalOutputJoltge")

    batteryBanks = readBatteryBanks("day03/example-input.txt")
    var totalOutputJoltgeL = sumMaximumJoltagesPart2(batteryBanks, 12)
    check(totalOutputJoltgeL == 3121910778619) { "Part 2 example fail, sum should be 3121910778619 but was $totalOutputJoltgeL" }

    batteryBanks = readBatteryBanks("day03/input.txt")
    totalOutputJoltgeL = sumMaximumJoltagesPart2(batteryBanks, 12)
    println("Part 2 total output joltge: $totalOutputJoltgeL")
}

fun sumMaximumJoltagesPart2(batteryBanks: List<List<Int>>, joltageDigits: Int): Long {

    var totalJoltge = 0L

    for (batteryBank in batteryBanks) {
        var batteryBankLeft = batteryBank.toMutableList()
        var sum = ""
        for (joltageDigit in joltageDigits downTo 1) {
            val selectedBattery = findNextBattery(batteryBankLeft, joltageDigit)
            batteryBankLeft = createBatteryBankLeft(batteryBankLeft, selectedBattery)
            sum += selectedBattery
        }
        totalJoltge += sum.toLong()
    }

    return totalJoltge
}

fun findNextBattery(batteryBank: List<Int>, joltageDigit: Int) =
    requireNotNull(batteryBank.take(batteryBank.size - joltageDigit + 1).maxOrNull())


fun createBatteryBankLeft(batteryBank: List<Int>, selectedBattery: Int): MutableList<Int> {
    val selectedBatteryIndex = batteryBank.indexOf(selectedBattery)
    return batteryBank.drop(selectedBatteryIndex + 1).toMutableList()
}

fun sumMaximumJoltagesPart1(batteryBanks: List<List<Int>>): Int {

    var sum = 0

    for (batteryBank in batteryBanks) {
        val firstBattery = findFirstBattery(batteryBank, 1)
        val index = batteryBank.indexOf(firstBattery)
        val secondBattery = findSecondBattery(batteryBank, index)

        sum += firstBattery * 10 + secondBattery
    }

    return sum
}

fun findFirstBattery(batteryBank: List<Int>, drop: Int) =
    requireNotNull(batteryBank.dropLast(drop).maxOrNull())

fun findSecondBattery(batteryBank: List<Int>, index: Int) =
    requireNotNull(batteryBank.subList(index + 1, batteryBank.size).maxOrNull())

fun readBatteryBanks(filePath: String): List<List<Int>> {
    val lines = readLines(filePath)
    return toBatteryBanks(lines)
}

fun toBatteryBanks(lines: List<String>): List<List<Int>> {
    return lines.map { line -> line.map { it.digitToInt() } }
}
