package com.adclock.util

import com.adclock.model.Direction
import com.adclock.model.Movement

const val MAX_STEPS = 1705

fun Direction.step(position: Int): Int {
    return if (this == Direction.FORWARD) {
        if (position == MAX_STEPS) 0 else position + 1
    } else {
        if (position == 0) MAX_STEPS else position - 1
    }
}

fun Movement.validate() {
    require(position >= 0) { "Position $position invalid. Must be >= 0" }
    require(position < MAX_STEPS) { "Position $position invalid. Must be < $MAX_STEPS" }

    require(waitSteps >= 0) { "WaitSteps $waitSteps invalid. Must be >= 0"  }
}