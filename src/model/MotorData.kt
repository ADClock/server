package com.adclock.model

import com.adclock.util.step

class MotorData {
    var position: Int = 0
        private set

    var waitSteps: Int = 0
        set(value) {
            require(value >= 0)
            field = value
        }

    fun step(target: Movement) = with(target) {
        if (waitSteps > 0) {
            waitSteps--
        } else {
            position = direction.step(position)
        }
    }

    fun clearPosition() {
        position = 0
    }
}
