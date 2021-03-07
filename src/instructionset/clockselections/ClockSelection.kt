package com.adclock.instructionset.clockselections

import com.adclock.model.Clock
import com.adclock.model.HandType


interface ClockSelection {
    val hands: Array<HandType>

    fun selected(clock: Clock): Boolean
}

