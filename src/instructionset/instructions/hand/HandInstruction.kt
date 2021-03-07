package com.adclock.instructionset.instructions.hand

import com.adclock.instructionset.clockselections.ClockSelection
import com.adclock.instructionset.instructions.clock.ClockInstruction
import com.adclock.model.Clock
import com.adclock.model.Hand
import com.adclock.model.HandType
import com.adclock.services.ClockInteractionService
import com.adclock.services.HandInteractionService

abstract class HandInstruction(selection: List<ClockSelection>) : ClockInstruction(selection) {
    abstract fun apply(handService: HandInteractionService, hand: Hand)

    override fun apply(clockService: ClockInteractionService, clock: Clock) {
        val selectedHands = selection.find { it.selected(clock) }?.hands ?: emptyArray()

        if (HandType.HOUR in selectedHands)
            apply(clockService.handService, clock.hour)

        if (HandType.MINUTE in selectedHands)
            apply(clockService.handService, clock.minute)
    }
}
