package com.mttp.gameoflife

import androidx.compose.runtime.saveable.listSaver

class GameGridViewModel {
}

val CitySaver = listSaver<GameGrid, Any>(
    save = {
        var cell = Array(width) { Array(height) {0} }
        var generation : Int = 0

        listOf(it.name, it.country)
           },
    restore = { City(it[0] as String, it[1] as String) }
)

