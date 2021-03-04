package com.adclock.util

import com.adclock.model.Direction

const val MAX_STEPS = 1705

fun Direction.step(position: Int): Int {
    return if (this == Direction.FORWARD) {
        if (position == MAX_STEPS) 0 else position + 1
    } else {
        if (position == 0) 0 else position - 1
    }
}