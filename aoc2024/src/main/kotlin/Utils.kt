package nl.openweb

import java.io.File

fun readInput(day: String, file: String = "input") = File("aoc2024/src/main/kotlin/$day/", "$file.txt").readLines()

fun readTestInput(day: String, file: String = "input") = File("aoc2024/src/main/kotlin/$day/", "test_$file.txt").readLines()

fun <E> Iterable<E>.indexesOf(e: E)
        = mapIndexedNotNull{ index, elem -> index.takeIf{ elem == e } }

fun splitListBasedOnEmptyElements(input: List<String>): List<List<String>> {
    val splitIndexes = input.indexesOf("")
    val splitIndexesWithStartAndEnd = listOf(0) + splitIndexes + listOf(input.size)
    val indexPairs = splitIndexesWithStartAndEnd.zipWithNext()
    val groupedElements = mutableListOf<List<String>>()
    indexPairs.forEach {
        groupedElements.add(input.subList(if (it.first == 0) 0 else it.first + 1, it.second))
    }
    return groupedElements
}