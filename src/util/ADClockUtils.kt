package com.adclock.util

import com.adclock.model.Clock
import com.adclock.model.Direction
import com.adclock.model.Movement
import com.adclock.instructionset.clockselections.ClockSelection

const val MAX_STEPS = 1705

fun Direction.step(position: Int): Int {
    return if (this == Direction.FORWARD) {
        if (position == MAX_STEPS) 0 else position + 1
    } else {
        if (position == 0) MAX_STEPS else position - 1
    }
}

fun Direction.move(position: Int, steps: Int) =
    (position + (if (this == Direction.FORWARD) steps else -steps) % MAX_STEPS + MAX_STEPS) % MAX_STEPS

fun Array<Clock>.filter(selection: List<ClockSelection>) = filter { c -> selection.any { it.selected(c) } }

