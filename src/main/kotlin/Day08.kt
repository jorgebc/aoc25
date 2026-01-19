import kotlin.math.abs

fun main() {

    println("AoC day 08")

    var mult = calculateThreeLargestCircuitMult("day08/example-input.txt", 10)
    val expectedMult = 40L
    check(mult == expectedMult) { "Part 1 example fail, multiplication should be $expectedMult but was $mult" }

    mult = calculateThreeLargestCircuitMult("day08/input.txt", 1000)
    println("Part 1 multiplication $mult")

}

fun calculateThreeLargestCircuitMult(filePath: String, desiredConnections: Int): Long {

    val lines = readLines(filePath)
    val junctionBoxes = createJunctionBoxes(lines)
    val circuits = mutableListOf<MutableSet<Point3D>>()
    var alreadyFoundMinDist2 = Long.MIN_VALUE

    repeat(desiredConnections) {
        val closest = findClosest(junctionBoxes, alreadyFoundMinDist2)
        alreadyFoundMinDist2 = closest.dist2
        addJunctionBoxToCircuit(closest, circuits)
    }

    val threeLargestCircuitsSizeMult =
        circuits.sortedByDescending { it.size }.take(3)
            .map { it.size.toLong() }.fold(1L) { acc, size -> acc * size }

    return threeLargestCircuitsSizeMult
}

fun addJunctionBoxToCircuit(junctionBox: Closest, circuits: MutableList<MutableSet<Point3D>>) {

    val indexes = getMergeableCircuitsIndexes(junctionBox, circuits)

    when (indexes.size) {
        0 -> {
            val newCircuit = mutableSetOf(junctionBox.p1, junctionBox.p2)
            circuits.add(newCircuit)
        }

        1 -> {
            val circuit = circuits[indexes.first()]
            circuit.add(junctionBox.p1)
            circuit.add(junctionBox.p2)
        }

        2 -> {
            val circuit1 = circuits[indexes.first()]
            val circuit2 = circuits[indexes.last()]
            circuits.remove(circuit1)
            circuits.remove(circuit2)
            val newCircuit = (circuit1 union circuit2).toMutableSet()
            newCircuit.add(junctionBox.p1)
            newCircuit.add(junctionBox.p1)
            circuits.add(newCircuit)
        }

        else -> error("Invalid number of circuit indexes to merge junction box")
    }
}

fun getMergeableCircuitsIndexes(junctionBox: Closest, circuits: MutableList<MutableSet<Point3D>>): MutableList<Int> {
    val indexes = mutableListOf<Int>()
    for ((index, circuit) in circuits.withIndex()) {
        if (circuit.contains(junctionBox.p1) || circuit.contains(junctionBox.p2)) {
            indexes.add(index)
        }
    }
    return indexes
}

fun createJunctionBoxes(lines: List<String>) =
    lines.map { line -> line.split(",").let { Point3D(it[0].toInt(), it[1].toInt(), it[2].toInt()) } }

fun findClosest(points: List<Point3D>, alreadyFoundMinDist2: Long): Closest {
    if (points.size < 10) return solveClosest(points, alreadyFoundMinDist2)

    val midIndex = points.size / 2
    val leftClosest = findClosest(points.dropLast(midIndex), alreadyFoundMinDist2)
    val rightClosest = findClosest(points.drop(midIndex), alreadyFoundMinDist2)

    val possibleClosest = if (leftClosest.dist2 < rightClosest.dist2) leftClosest else rightClosest
    val minDist2 = possibleClosest.dist2

    val midX = points[midIndex].x
    val middleStrip = getMiddleStrip(points, midX, minDist2)
    val midClosest = findMidClosest(middleStrip, minDist2, alreadyFoundMinDist2)

    return if (possibleClosest.dist2 < midClosest.dist2) possibleClosest else midClosest
}

fun getMiddleStrip(points: List<Point3D>, xReference: Int, minDist2: Long) =
    points.filter { abs(it.x - xReference) < minDist2 }.sortedBy { it.y }

fun findMidClosest(points: List<Point3D>, dist2: Long, alreadyFoundMinDist2: Long): Closest {
    require(points.size >= 2)

    var smallestDist2 = points.first().dist2(points.last())
    var closest = Closest(points.first(), points.last(), smallestDist2)

    for ((p1Index, p1) in points.withIndex()) {
        for (p2Index in p1Index + 1..points.lastIndex) {

            val p2 = points[p2Index]
            if (p1.y - p2.y < dist2 && p1.z - p2.z < dist2) {
                val possibleDist2 = p1.dist2(p2)
                if (possibleDist2 > alreadyFoundMinDist2 && possibleDist2 < smallestDist2) {
                    smallestDist2 = possibleDist2
                    closest = Closest(p1, p2, smallestDist2)
                }
            }
        }
    }

    return closest
}

fun solveClosest(points: List<Point3D>, alreadyFoundMinDist2: Long): Closest {
    require(points.size >= 2)

    var smallestDist2 = points.first().dist2(points.last())
    var closest = Closest(points.first(), points.last(), smallestDist2)

    for ((p1Index, p1) in points.withIndex()) {
        for (p2Index in p1Index + 1..points.lastIndex) {

            val p2 = points[p2Index]
            val dist2 = p1.dist2(p2)
            if (dist2 > alreadyFoundMinDist2 && dist2 < smallestDist2) {
                smallestDist2 = dist2
                closest = Closest(p1, p2, smallestDist2)
            }
        }
    }

    return closest
}

data class Closest(val p1: Point3D, val p2: Point3D, val dist2: Long)

data class Point3D(val x: Int, val y: Int, val z: Int) {
    fun dist2(o: Point3D): Long {
        val dx = x - o.x
        val dy = y - o.y
        val dz = z - o.z
        return dx.toLong() * dx + dy.toLong() * dy + dz.toLong() * dz
    }
}
