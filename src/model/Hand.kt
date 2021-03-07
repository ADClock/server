package com.adclock.model

class Hand {
    // current position of clock hand
    val current = MotorData()

    // target position of clock hand
    var target = Movement()
        private set

    // planned position of clock hand
    var planned = Movement()

    fun tryStep() {
        if (current.position != target.position)
            current.step(target)
    }

    fun updateTarget() {
        target = planned.copy()
        current.waitSteps = target.waitSteps
    }

    fun init() {
        current.clearPosition()
        current.waitSteps = 0
    }
}
