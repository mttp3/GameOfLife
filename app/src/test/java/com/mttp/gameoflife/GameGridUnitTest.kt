package com.mttp.gameoflife

import org.junit.Test
import org.junit.Assert.*

class GameGridUnitTest {
    @Test
    fun countZeroNeighbour() {
        val grid = GameGrid(3, 3)
        assertEquals( 0, grid.neighbours(1, 1))
    }
    @Test
    fun neighborhoodWithOneCell() {
        var grid = GameGrid(3, 3)
        grid.cell[0][0] = 1
        assertEquals( 1, grid.neighbours(1, 1))
        assertEquals( 1, grid.neighbours(0, 1))
        assertEquals( 0, grid.neighbours(0, 0))
        assertEquals( 0, grid.neighbours(2, 2))
    }
    @Test
    fun countThreeNeighbours() {
        var grid = GameGrid(3, 3)
        grid.cell[1][0] = 1
        grid.cell[2][2] = 1
        grid.cell[0][2] = 1
        assertEquals( 3, grid.neighbours(1, 1))
    }
    @Test
    fun neighboursDontIncludeSelf() {
        var grid = GameGrid(3, 3)
        grid.cell[1][0] = 1
        grid.cell[1][1] = 1
        assertEquals( 1, grid.neighbours(1, 1))
    }

    @Test
    fun evolveWith2Neighbours() {
        var grid = GameGrid(2, 2)
        grid.cell[0][0] = 1
        grid.cell[1][0] = 1
        grid.cell[0][1] = 1
        grid.evolve()
        assertEquals(1, grid.cell[1][1])
    }

    @Test
    fun evolveDontWriteOriginalGrid() {
        var grid = GameGrid(3, 1)
        grid.cell[0][0] = 1
        grid.cell[1][0] = 1
        grid.cell[2][0] = 1
        assertEquals(1, grid.neighbours(0, 0))
        assertEquals(2, grid.neighbours(1, 0))
        assertEquals(1, grid.neighbours(2, 0))
        grid.evolve()
        assertEquals(0, grid.cell[0][0])
        assertEquals(1, grid.cell[1][0])
        assertEquals(0, grid.cell[2][0])
    }

}