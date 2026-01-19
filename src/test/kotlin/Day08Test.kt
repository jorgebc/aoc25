import kotlin.test.Test
import kotlin.test.assertEquals

class Day08Test {

    @Test
    fun `Square distance should be 0`() {
        val p1 = Point3D(1, 2, 3)
        val p2 = Point3D(1, 2, 3)
        val distance = p1.dist2(p2)
        assertEquals(0, distance)
    }

    @Test
    fun `Square distance should be different to 0`() {
        val p1 = Point3D(1, 2, 3)
        val p2 = Point3D(1, 2, 4)
        val distance = p1.dist2(p2)
        assertEquals(1, distance)
    }

    @Test
    fun `Solve closest should return the closest from a list of two points`() {
        val p1 = Point3D(1, 2, 3)
        val p2 = Point3D(1, 2, 4)
        val dist2 = p1.dist2(p2)
        val closest = solveClosest(listOf(p1, p2), Long.MIN_VALUE)
        assertEquals(Closest(p1, p2, dist2), closest)
    }

    @Test
    fun `Solve closest should return the closest from a list of three points`() {
        val p1 = Point3D(1, 2, 3)
        val p2 = Point3D(1, 2, 4)
        val p3 = Point3D(3, 3, 3)
        val dist2 = p1.dist2(p2)
        val closest = solveClosest(listOf(p1, p2, p3), Long.MIN_VALUE)
        assertEquals(Closest(p1, p2, dist2), closest)
    }

    @Test
    fun `Find closest should return the closest from a list of points`() {
        val p1 = Point3D(162, 817, 812)
        val p2 = Point3D(57, 618, 57)
        val p3 = Point3D(906, 360, 560)
        val p4 = Point3D(592, 479, 940)
        val p5 = Point3D(352, 342, 300)
        val p6 = Point3D(466, 668, 158)
        val p7 = Point3D(542, 29, 236)
        val p8 = Point3D(431, 825, 988)
        val p9 = Point3D(52, 470, 668)
        val p0 = Point3D(216, 146, 977)
        val p11 = Point3D(425, 690, 689)

        val dist2 = p11.dist2(p1)
        val closest = findClosest(
            listOf(p1, p2, p3, p4, p5, p6, p7, p8, p9, p0, p11).sortedBy { it.x },
            Long.MIN_VALUE
        )

        assertEquals(Closest(p11, p1, dist2), closest)
    }

    @Test
    fun `Create junction boxes should create a list of 3d points`() {
        val lines = listOf("162,817,812", "57,618,57")

        val junctionBoxes = createJunctionBoxes(lines)

        val expectedJunctionBoxes = listOf(Point3D(162, 817, 812), Point3D(57, 618, 57))
        assertEquals(expectedJunctionBoxes, junctionBoxes)
    }
}