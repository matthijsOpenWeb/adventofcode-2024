package nl.openweb.day2

import nl.openweb.readInput
import nl.openweb.readTestInput
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {

    val day = "2"

    fun isSafe(it: String): Boolean {
        val range = it.split(" ").map { it.toInt() }
        range.zipWithNext().forEach {
            if ((it.first - it.second).absoluteValue !in 1..3) {
                return false
            }
        }

        return range == range.sortedDescending() || range == range.sorted()
    }

    fun part1(input: List<String>): Int {
        return input.count { isSafe(it) }
    }

    fun isSafeWithDampener(range: List<Int>, first: Boolean): Boolean {
        val sign1 = (range[0] - range[1]).sign
        val sign2 = (range[1] - range[2]).sign
        val sign3 = (range[2] - range[3]).sign
        val sign = listOf(sign1, sign2, sign3).groupingBy { it }.eachCount().maxBy {it.value }.key
        range.zipWithNext().forEachIndexed {index, it ->
            if ((it.first - it.second).absoluteValue !in 1..3 ||
                (it.first - it.second).sign != sign) {
                return if(first) {
                    val mutableList1 = range.toMutableList()
                    val mutableList2 = range.toMutableList()
                    mutableList1.removeAt(index)
                    mutableList2.removeAt(index + 1)

                    isSafeWithDampener(mutableList1, false) || isSafeWithDampener(mutableList2, false)
                } else {
                    false
                }
            }
        }
        return true
    }

    fun part2(input: List<String>): Int {
        println("unsafe: ${input.count { r ->
            val range = r.split(" ").map { it.toInt() }
            !isSafeWithDampener(range, true)
        }
        }")

        return input.count { r ->
            val range = r.split(" ").map { it.toInt() }
            isSafeWithDampener(range, true) }
    }

    val input = readInput("day$day", "input2")
//    val input = readTestInput("day$day", "input2")
    val input2 = readInput("day$day", "input2")
//    val input2 = readTestInput("day$day", "input2")
    println("safe ranges ${part1(input)}")
    println("safe with dampener ${part2(input2)}")
}