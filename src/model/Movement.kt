package com.adclock.model

data class Movement(
    var position: Int = 0,
    var waitSteps: Int = 0,
    var stepDelay: Int = 0,
    var direction: Direction = Direction.FORWARD
)
