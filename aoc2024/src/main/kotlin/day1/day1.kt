package nl.openweb.day1

import nl.openweb.readInput
import kotlin.math.absoluteValue

fun main() {

    val day = "1"

    fun part1(input: List<String>): Int {
        val list1 = input.map {
            it.split("   ")[0].toInt()
        }.sorted()
        val list2 = input.map {
            it.split("   ")[1].toInt()
        }.sorted()
        return list1.mapIndexed { index, i -> (i - list2[index]).absoluteValue }.sum()
    }

    fun part2(input: List<String>): Int {
        val list1 = input.map {
            it.split("   ")[0].toInt()
        }.sorted()
        val list2 = input.map {
            it.split("   ")[1].toInt()
        }.sorted()

        return list1.sumOf { i -> list2.count { it == i } * i }
    }

    val input = readInput("day$day", "input1")
//    val input = readTestInput("day$day", "input1")
    val input2 = readInput("day$day", "input1")
//    val input2 = readTestInput("day$day", "input1")
    println("Total distance between smallest values ${part1(input)}")
    println("Similarity score left list ${part2(input2)}")
}