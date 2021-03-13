package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstruction
import com.adclock.model.Clock
import com.adclock.model.Hand
import com.adclock.model.HandType

abstract class HandInstruction(selection: List<ClockSelection>) : ClockInstruction(selection) {
    abstract fun apply(hand: Hand)

    override fun apply(clock: Clock) {
        val selectedHands = selection.find { it.selected(clock) }?.hands ?: emptyArray()

        if (HandType.HOUR in selectedHands)
            apply(clock.hour)

        if (HandType.MINUTE in selectedHands)
            apply(clock.minute)
    }
}
