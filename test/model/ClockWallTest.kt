package com.adclock.model

import kotlin.test.Test
import kotlin.test.assertEquals

class ClockWallTest {

    @Test
    fun emptyClockWall() {
        val wall = ClockWall(0, 0)
        assertEquals(0, wall.clocks.size)
    }


    @Test
    fun singleClockClockWall() {
        val wall = ClockWall(1, 1)
        assertEquals(1, wall.clocks.size)
        assertEquals(1, wall.clocks.first().id)
        assertEquals(0, wall.clocks.first().posX)
        assertEquals(0, wall.clocks.first().posY)
    }


    @Test
    fun quadClockClockWall() {
        val wall = ClockWall(2, 2)
        assertEquals(4, wall.clocks.size)

        // first clock
        assertEquals(1, wall.clocks.first().id)
        assertEquals(0, wall.clocks.first().posX)
        assertEquals(0, wall.clocks.first().posY)
        // 2nd clock
        assertEquals(2, wall.clocks.component2().id)
        assertEquals(0, wall.clocks.component2().posX)
        assertEquals(1, wall.clocks.component2().posY)
        // 3rd clock
        assertEquals(3, wall.clocks.component3().id)
        assertEquals(1, wall.clocks.component3().posX)
        assertEquals(0, wall.clocks.component3().posY)
        // 4th clock
        assertEquals(4, wall.clocks.component4().id)
        assertEquals(1, wall.clocks.component4().posX)
        assertEquals(1, wall.clocks.component4().posY)
    }


    @Test
    fun clockWallGetClock() {
        val wall = ClockWall(5, 3)
        wall.clocks.forEach { comp ->
            assertEquals(comp.id, wall.getClock(comp.posX, comp.posY).id, "GetClock for x${comp.posX} y${comp.posY} ")
        }
    }
}