import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun `Single battery bank should be mapped correctly`() {

        val lines = listOf("123")
        val batteryBanks = toBatteryBanks(lines)

        assertEquals(listOf(listOf(1, 2, 3)), batteryBanks)
    }

    @Test
    fun `Find first battery should not take in account last digit`() {
        val batteryBank = listOf(1, 2, 3)
        val battery = findFirstBattery(batteryBank, 1)

        assertEquals(2, battery)
    }

    @Test
    fun `Find second battery should start at index`() {
        val batteryBank = listOf(1, 2, 3)
        val index = batteryBank.indexOf(2)
        val battery = findSecondBattery(batteryBank, index)

        assertEquals(3, battery)
    }

    @Test
    fun `Sum maximum joltage should calculate correctly`() {
        val batteryBanks = listOf(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1))
        val sum = sumMaximumJoltagesPart1(batteryBanks)
        assertEquals(98, sum)
    }

    @Test
    fun `Find first battery should not take in account 12 last digits`() {
        val batteryBank = listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)
        val battery = findFirstBattery(batteryBank, 12)

        assertEquals(4, battery)
    }

    @Test
    fun `First battery in find next battery should not take in account 11 last batteries`() {
        val batteryBank = listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)
        val battery = findNextBattery(batteryBank, 12)

        assertEquals(4, battery)
    }

    @Test
    fun `Create battery bank left should sublist from index`() {
        val batteryBank = listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)
        val battery = createBatteryBankLeft(batteryBank, 4)

        assertEquals(listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8), battery)
    }

    @Test
    fun `Second battery in find next battery should not take in account 10 last batteries`() {
        val batteryBank = listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8)
        val battery = findNextBattery(batteryBank, 11)

        assertEquals(3, battery)
    }

    @Test
    fun `Third battery in find next battery should not take in account 9 last batteries`() {
        val batteryBank = listOf(4, 2, 3, 4, 2, 3, 4, 2, 7, 8)
        val battery = findNextBattery(batteryBank, 10)

        assertEquals(4, battery)
    }
}