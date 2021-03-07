package com.adclock.services

import com.adclock.model.Direction
import com.adclock.model.Hand
import com.adclock.util.MAX_STEPS
import com.adclock.util.move

class HandInteractionService {

    fun setPlannedPosition(hand: Hand, position: Int, relative: Boolean) {
        require(position >= 0) { "Position $position invalid. Must be >= 0" }
        require(position < MAX_STEPS) { "Position $position invalid. Must be < $MAX_STEPS" }
        hand.planned.position = if (relative) hand.planned.direction.move(hand.planned.position, position) else position
    }

    fun setPlannedSpeed(hand: Hand, speed: Int) {
        hand.planned.stepDelay = speed
    }

    fun setPlannedDirection(hand: Hand, direction: Direction) {
        hand.planned.direction = direction
    }

    fun setPlannedWaitSteps(hand: Hand, waitSteps: Int) {
        require(waitSteps >= 0) { "WaitSteps $waitSteps invalid. Must be >= 0" }
        hand.planned.waitSteps = waitSteps
    }

    fun resetPlanned(hand: Hand) {
        hand.planned = hand.target.copy()
    }

}