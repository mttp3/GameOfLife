package com.mttp.gameoflife

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun gamegrid_canBeCreated() {
        val grid = GameGrid()
        assertNotNull(grid)
    }
    @Test
    fun grid_hasDimensions() {
        val grid = GameGrid()
        assertTrue(grid.height > 0)
        assertTrue(grid.width > 0)
    }
    @Test
    fun grid_hasCells() {
        val grid =GameGrid()
        assertEquals(0, grid.cell[0][1] )
    }
    @Test
    fun grid_buildDimensions() {
        val grid = GameGrid(2, 5)
        assertEquals(2, grid.width)
        assertEquals(5, grid.height)
    }
    @Test
    fun grid_cellDimOrder() {
        val grid = GameGrid(2, 5)
        assertEquals( 0, grid.cell[1][4])
    }
    @Test
    fun grid_canChangeCell() {
        val grid = GameGrid()
        grid.cell[0][5] = 1
        assertEquals(1, grid.cell[0][5])
    }
    @Test
    fun grid_canCountNeighbors() {
        val grid = GameGrid()
        assertEquals(0, grid.getNeighborCount(1, 1))
        grid.cell[0][0] = 1
        assertEquals(1, grid.getNeighborCount(1, 1))
        grid.cell[1][1] = 1
        assertEquals(1, grid.getNeighborCount(1, 1))
        grid.cell[2][2] = 1
        assertEquals(2, grid.getNeighborCount(1, 1))
    }
}