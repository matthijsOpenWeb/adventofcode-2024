package nl.openweb.day4

import nl.openweb.readInput
import nl.openweb.readTestInput


fun main() {

    val day = "4"


    fun countXmas(s: String) : Int {
        val regex = Regex("XMAS")
        return regex.findAll(s).count()
    }

    fun indexMas(s: String) : List<Int> {
        val regex = Regex("MA(?=S)|SA(?=M)")
        return regex.findAll(s).map { it.range.first + 1 }.toList()
    }

    fun getVerticals(input: List<String>): List<String> {
        return (0 until input[0].length).map { x ->
            input.map { it[x] }.joinToString("")
        }
    }

    fun getDiagonals(input: List<String>): List<String> {
        val diagonals1 = (0 until input[0].length).map { x ->
            input.mapIndexed { y, l ->
                if (x + y < l.length) l[x + y] else ""
            }.joinToString("")
        }

        val diagonals2 = (1 until input.size).map { y ->
            (0 until input[1].length).map { x ->
                if(y + x < input.size) input[y + x][x] else ""
            }.joinToString ("")
        }

        return diagonals1 + diagonals2
    }

    fun getDiagonalsIndices(input: List<String>, reversed: Boolean = false): List<Pair<Int, Int>> {
        val diagonals1 = (0 until input[0].length).map { x ->
            input.mapIndexed { y, l ->
                if (x + y < l.length) l[x + y] else ""
            }.joinToString("")
        }

        val diagonals2 = (1 until input.size).map { y ->
            (0 until input[1].length).map { x ->
                if(y + x < input.size) input[y + x][x] else ""
            }.joinToString ("")
        }

        if(reversed) {
            return diagonals1.flatMapIndexed{i, l ->
                indexMas(l).map { Pair(it, input[0].length - 1 - (i + it)) }
            } +
            diagonals2.flatMapIndexed{i, l ->
                indexMas(l).map { Pair(1 + i + it, input[0].length - 1 - it) }
            }
        }

        return diagonals1.flatMapIndexed{i, l ->
            indexMas(l).map { Pair(it, i + it) }
        } +
        diagonals2.flatMapIndexed{i, l ->
            indexMas(l).map { Pair(1 + i + it, it) }
        }
    }

    fun part1(input: List<String>): Int {
        val horizontal = input.sumOf { l -> countXmas(l) + countXmas(l.reversed()) }
        val vertical = getVerticals(input).sumOf { l -> countXmas(l) + countXmas(l.reversed()) }
        val diagonal = (getDiagonals(input) + getDiagonals(input.map { it.reversed() }))
            .sumOf { l -> countXmas(l) + countXmas(l.reversed()) }
        return horizontal + vertical + diagonal
    }


    fun part2(input: List<String>): Int {
        //get diagonals with mas with index of A
        //get diagonals with mas with index of A reversed
        //cross check indeces of A and count overlap
        val diagonal = getDiagonalsIndices(input)
        val diagonalReversed = getDiagonalsIndices(input.map { it.reversed() }, true)
        return diagonal.count { diagonalReversed.contains(it) }
    }

    val input = readInput("day$day", "input$day")
//    val input = readTestInput("day$day", "input$day")
    val input2 = readInput("day$day", "input$day")
//    val input2 = readTestInput("day$day", "input$day")
    println("nr of xmas ${part1(input)}")
    println("nr of mas in cross ${part2(input2)}")
}