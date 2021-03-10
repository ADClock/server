package com.adclock.services

import com.adclock.model.ClockWall

class WallInteractionService(
    val clockService: ClockInteractionService
) {

    fun initialize(wall: ClockWall) {
        // Or a broadcast init?
        wall.clocks.forEach { clockService.sendInit(it) }
    }

    fun sendUpdate(wall: ClockWall) {
        wall.clocks.forEach { clockService.sendUpdate(it) }
    }
}