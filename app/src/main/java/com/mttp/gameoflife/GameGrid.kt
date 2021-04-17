package com.mttp.gameoflife

class GameGrid( val width: Int = 10, val height: Int = 10 ) {
    var cell = Array(width) { Array(height) {0} }
    fun getNeighborCount (x: Int, y: Int): Int {
        var count = 0
        for(cx in (x-1)..(x+1)) {
            for (cy in (y-1)..(y+1)) {
                if ((cx != x) || (cy != y)) {
                    if (cell[cx][cy] != 0) {
                        count++
                    }
                }
            }
        }
        return count
    }
}