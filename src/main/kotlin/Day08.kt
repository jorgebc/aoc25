import kotlin.math.abs

fun main() {

    println("AoC day 08")

    var mult = calculateThreeLargestCircuitMult("day08/example-input.txt")
    val expectedMult = 21L
    check(mult == expectedMult) { "Part 1 example fail, multiplication should be $expectedMult but was $mult" }

    mult = calculateThreeLargestCircuitMult("day08/input.txt")
    println("Part 1 multiplication $mult")

}

fun calculateThreeLargestCircuitMult(string: String): Long {
    TODO("Not yet implemented")
}

fun findClosest(points: List<Point3D>): Closest {
    if (points.size < 10) return solveClosest(points)

    val midIndex = points.size / 2
    val leftClosest = findClosest(points.dropLast(midIndex))
    val rightClosest = findClosest(points.drop(midIndex))

    val possibleClosest = if (leftClosest.dist2 < rightClosest.dist2) leftClosest else rightClosest
    val minDist2 = possibleClosest.dist2

    val midX = points[midIndex].x
    val filtered = points.filter { abs(it.x - midX) < minDist2 }.sortedBy { it.y }
    val midClosest = findMidClosest(filtered, minDist2)

    return if (possibleClosest.dist2 < midClosest.dist2) possibleClosest else midClosest
}

fun findMidClosest(points: List<Point3D>, dist2: Long): Closest {
    require(points.size >= 2)
    var smallestDist2 = points.first().dist2(points.last())
    var closest = Closest(points.first(), points.last(), smallestDist2)

    for ((p1Index, p1) in points.withIndex()) {
        for (p2Index in p1Index + 1..points.lastIndex) {
            val p2 = points[p2Index]
            if (p1.y - p2.y < dist2 && p1.z - p2.z < dist2) {
                val possibleDist2 = p1.dist2(p2)
                if (possibleDist2 < smallestDist2) {
                    smallestDist2 = possibleDist2
                    closest = Closest(p1, p2, smallestDist2)
                }
            }
        }
    }

    return closest
}

fun solveClosest(points: List<Point3D>): Closest {
    require(points.size >= 2)
    var smallestDist2 = points.first().dist2(points.last())
    var closest = Closest(points.first(), points.last(), smallestDist2)

    for ((p1Index, p1) in points.withIndex()) {
        for (p2Index in p1Index + 1..points.lastIndex) {

            val p2 = points[p2Index]
            val dist2 = p1.dist2(p2)
            if (dist2 < smallestDist2) {
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
