package com.adclock.services

import com.adclock.hardware.ClockCommunication
import com.adclock.model.Clock

class ClockInteractionService(
    val handService: HandInteractionService
) {

    fun sendUpdate(clock: Clock) {
        ClockCommunication.sendUpdate(clock)
        clock.updateTarget()
    }

    fun sendInit(clock: Clock) {
        ClockCommunication.sendInit(clock)
        clock.init()
    }

}