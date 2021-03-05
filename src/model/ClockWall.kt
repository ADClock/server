package com.adclock.model

class ClockWall(val sizeX: Int, val sizeY: Int) {

    val clocks = Array(sizeX * sizeY) { Clock(it, it / sizeX, it % sizeX) }

    fun getClock(x: Int, y: Int) = clocks[sizeX * x + y]
}