package com.mttp.gameoflife

import kotlin.random.Random

class GameGrid( val width: Int = 10, val height: Int = 10 ) {
    var cell = Array(width) { Array(height) {0} }
    var generation : Int = 0

    fun randomize(): GameGrid {
        for (x in 0 until width) {
            for (y in 0 until height) {
                cell[x][y] = if (Random.nextBoolean()) 1 else 0
            }
        }
        generation = 0
        return this
    }

    fun clear() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                cell[x][y] = 0
            }
        }
        generation = 0
    }

    fun neighbours(x: Int, y: Int): Int {
        val xMin = (x - 1).coerceAtLeast(0)
        val xMax = (x + 1).coerceAtMost(width-1)
        val yMin = (y - 1).coerceAtLeast(0)
        val yMax = (y + 1).coerceAtMost(height-1)
        var count = 0
        for (xCur in xMin..xMax) {
            for (yCur in yMin..yMax) {
                if ((xCur != x) or (yCur != y)) {
                    count += cell[xCur][yCur]
                }
            }
        }
        return count
    }

    fun evolve(): GameGrid {
        var nextCell = cell.map { it.copyOf() }.toTypedArray()

        for (x in 0 until width) {
            for (y in 0 until height) {
                nextCell[x][y] = when (neighbours(x, y)) {
                    3 -> 1
                    2 -> if (cell[x][y] == 1) 1 else 0
                    else -> 0
                }
            }
        }
        cell = nextCell
        generation++
        return this
    }
}
