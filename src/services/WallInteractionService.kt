package com.adclock.services

import com.adclock.model.ClockWall

class WallInteractionService(
    val wall: ClockWall,
    val clockService: ClockInteractionService
) {

    fun initialize() {
        // Or a broadcast init?
        wall.clocks.forEach { clockService.sendInit(it) }
    }

    fun sendUpdate() {
        wall.clocks.forEach { clockService.sendUpdate(it) }
    }
}