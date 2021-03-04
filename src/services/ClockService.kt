package com.adclock.services

import com.adclock.dto.HandUpdateRequest
import com.adclock.hardware.ClockCommunication
import com.adclock.model.ClockWall
import com.adclock.model.HandType
import com.adclock.util.validate

@Suppress("unused")
class ClockService(private val wall: ClockWall) {

    fun initialize() {
        // Or a broadcast init?
        wall.clocks.forEach { ClockCommunication.sendInit(it) }
    }

    fun sendUpdate() {
        wall.clocks.forEach { ClockCommunication.sendUpdate(it) }
    }

    fun list() = wall.clocks

    fun updateHand(request: HandUpdateRequest) {
        request.update.validate()
        val clock = wall.getClock(request.x, request.y)
        val hand = if (request.handType == HandType.HOUR) clock.hour else clock.minute
        hand.planned = request.update
    }
}