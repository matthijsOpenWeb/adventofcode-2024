package nl.openweb.day3

import nl.openweb.readInput
import nl.openweb.readTestInput


fun main() {

    val day = "3"


    fun handleSingleMul(mul: String): Int {
        return mul.removePrefix("mul(").removeSuffix(")")
            .split(",").map { it.toInt() }.reduce(Int::times)
    }

    fun part1(input: List<String>): Int {
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)")
        return input.sumOf { l ->
            regex.findAll(l).sumOf { handleSingleMul(it.value) }
        }
    }


    fun part2(input: List<String>): Int {
        val regex = Regex("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)")
        var calc = true
        return input.sumOf { l ->
            regex.findAll(l).sumOf {
                when (it.value) {
                    "do()" -> {
                        calc = true
                        0
                    }

                    "don't()" -> {
                        calc = false
                        0
                    }

                    else -> if (calc) {
                        handleSingleMul(it.value)
                    } else {
                        0
                    }
                }
            }
        }
    }

    val input = readInput("day$day", "input$day")
//    val input = readTestInput("day$day", "input$day")
    val input2 = readInput("day$day", "input$day")
//    val input2 = readTestInput("day$day", "input$day")
    println("mul operations ${part1(input)}")
    println("with do mul ${part2(input2)}")
}