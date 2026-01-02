const val INITIAL_DIAL_POSITION = 50
const val MAX_DIAL_POSITION = 99
const val ROTATION_LEFT = 'L'

fun main() {
    println("AoC day 01")

    var password: Int
    password = day01PartOne("day01/example-input.txt")
    check(password == 3) { "Part 1 example fail, password should be 3 but was $password" }

    password = day01PartOne("day01/input.txt")
    println("Part 1 password: $password")

    password = day01PartTwo("day01/example-input.txt")
    check(password == 6) { "Part 2 example fail, password should be 6 but was $password" }

    password = day01PartTwo("day01/input.txt")
    println("Part 2 password: $password")
}

fun day01PartTwo(filePath: String): Int {
    val rotations = readRotations(filePath)

    var dialPosition = INITIAL_DIAL_POSITION
    var password = 0

    for ((direction, distance) in rotations) {
        val leftRotation = direction == ROTATION_LEFT

        repeat(distance) {
            dialPosition = rotateDial(leftRotation, dialPosition)
            if (dialPosition == 0) {
                password++
            }
        }
    }

    return password
}


fun day01PartOne(filePath: String): Int {
    val rotations = readRotations(filePath)

    var dialPosition = INITIAL_DIAL_POSITION
    var password = 0

    for ((direction, distance) in rotations) {
        val leftRotation = direction == ROTATION_LEFT

        repeat(distance) {
            dialPosition = rotateDial(leftRotation, dialPosition)
        }

        if (dialPosition == 0) password++

    }

    return password
}

fun rotateDial(leftRotation: Boolean, dialPosition: Int): Int {
    var newDialPosition = dialPosition
    if (leftRotation) newDialPosition-- else newDialPosition++
    if (newDialPosition < 0) newDialPosition = MAX_DIAL_POSITION
    if (newDialPosition > MAX_DIAL_POSITION) newDialPosition = 0
    return newDialPosition
}

fun readRotations(filePath: String): List<Pair<Char, Int>> =
    readLines(filePath).map { line -> line.first() to line.drop(1).toInt() }



