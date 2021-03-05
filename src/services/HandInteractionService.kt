package com.adclock.services

import com.adclock.model.Direction
import com.adclock.model.Hand
import com.adclock.util.move

class HandInteractionService {

    fun setPlannedPosition(hand: Hand, position: Int, relative: Boolean) {
        hand.planned.position = if (relative) position else hand.planned.direction.move(hand.planned.position, position)
    }

    fun setPlannedSpeed(hand: Hand, speed: Int) {
        hand.planned.stepDelay = speed
    }

    fun setPlannedDirection(hand: Hand, direction: Direction) {
        hand.planned.direction = direction
    }

    fun resetPlanned(hand: Hand) {
        hand.planned = hand.target.copy()
    }

}